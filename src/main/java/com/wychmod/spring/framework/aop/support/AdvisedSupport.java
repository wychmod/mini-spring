package com.wychmod.spring.framework.aop.support;

import com.wychmod.spring.framework.aop.aspect.Advice;
import com.wychmod.spring.framework.aop.aspect.AfterReturningAdviceInterceptor;
import com.wychmod.spring.framework.aop.aspect.AspectJAfterThrowingAdvice;
import com.wychmod.spring.framework.aop.aspect.MethodBeforeAdviceInterceptor;
import com.wychmod.spring.framework.aop.config.AopConfig;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 解析AOP配置的工具类
 * @author: wychmod
 * @date: 2023/6/28
 */
public class AdvisedSupport {

    private AopConfig config;
    private Object target;
    private Class<?> targetClass;
    private Pattern pointCutClassPattern;
    // 存储每个方法对应的各个切面,底层是list实现，返回一个list链
//    private Map<Method, Map<String, Advice>> methodCache;
    private Map<Method, List<Object>> methodCache;

    public AdvisedSupport(AopConfig config) {
        this.config = config;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        parse();
    }

    /**
     * 解析配置文件的方法
     */
    private void parse() {
        //把Spring的Excpress变成Java能够识别的正则表达式
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");

        // public .* com\.wychmod\.demo\.service\..*Service\..*\(.*\) -> public .* com\.wychmod\.demo\.service\..*Service
        // 保存专门匹配Class的正则
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        // com\.wychmod\.demo\.service\..*Service -> class com\.wychmod\.demo\.service\..*Service
        this.pointCutClassPattern = Pattern.compile("class " + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1));

        // 享元的共享池
        methodCache = new HashMap<Method, List<Object>>();
        // 保存专门匹配方法的正则
        Pattern pointCutPattern = Pattern.compile(pointCut);

        try {
            // 切面类
            Class aspectClass = Class.forName(this.config.getAspectClass());
            // 存储切面类中方法名和方法的映射
            Map<String,Method> aspectMethods = new HashMap<String, Method>();
            for (Method method : aspectClass.getMethods()) {
                aspectMethods.put(method.getName(), method);
            }

            for (Method method : this.targetClass.getMethods()) {
                String methodString = method.toString();
                // 去除method中的trows
                if(methodString.contains("throws")){
                    methodString = methodString.substring(0,methodString.lastIndexOf("throws")).trim();
                }

                // 正则匹配成功，放入各切面与Advice的映射，Advice(切面类实例， 切面类的方法)
                Matcher matcher = pointCutPattern.matcher(methodString);
                if (matcher.matches()){
//                    Map<String,Advice> advices = new HashMap<String, Advice>();
                    List<Object> advices = new LinkedList<Object>();

                    if(!(null == config.getAspectBefore() || "".equals(config.getAspectBefore()))){
                        advices.add(new MethodBeforeAdviceInterceptor(aspectMethods.get(config.getAspectBefore()), aspectClass.newInstance()));
                    }
                    if(!(null == config.getAspectAfter() || "".equals(config.getAspectAfter()))){
                        advices.add(new AfterReturningAdviceInterceptor(aspectClass.newInstance(),aspectMethods.get(config.getAspectAfter())));
                    }
                    if(!(null == config.getAspectAfterThrow() || "".equals(config.getAspectAfterThrow()))){
                        AspectJAfterThrowingAdvice advice = new AspectJAfterThrowingAdvice(aspectClass.newInstance(),aspectMethods.get(config.getAspectAfterThrow()));
                        advice.setThrowName(config.getAspectAfterThrowingName());
                        advices.add(advice);
                    }

                    //跟目标代理类的业务方法和Advices(<String,Advice>)建立一对多个关联关系，以便在Porxy类中获得
                    methodCache.put(method,advices);
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 给ApplicationContext首先IoC中的对象初始化时调用，决定要不要生成代理类的逻辑
     */
    public boolean pointCutMath() {
        return this.pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    /**
     * 根据一个目标代理类的方法，获得其对应的通知
     * @param method 代理类的方法
     */
    //根据一个目标代理类的方法，获得其对应的通知
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception {

        // 从缓存中获取
        List<Object> cached = this.methodCache.get(method);
        // 缓存未命中，则进行下一步处理
        if (cached == null) {
            Method m = targetClass.getMethod(method.getName(),method.getParameterTypes());
            cached = this.methodCache.get(m);
            // 存入缓存
            this.methodCache.put(m, cached);
        }
        return cached;
    }

//    public Map<String, Advice> getAdvices(Method method, Object o) throws Exception {
//        // 享元设计模式的应用
//        Map<String,Advice> cache = methodCache.get(method);
//        if(null == cache) {
//            Method m = this.targetClass.getMethod(method.getName(), method.getParameterTypes());
//            cache = methodCache.get(m);
//            this.methodCache.put(m,cache);
//        }
//        return cache;
//    }
}
