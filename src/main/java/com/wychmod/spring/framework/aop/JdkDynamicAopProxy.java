package com.wychmod.spring.framework.aop;

import com.wychmod.spring.framework.aop.aspect.Advice;
import com.wychmod.spring.framework.aop.support.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @description: 代理对象
 * @author: wychmod
 * @date: 2023/6/28
 */
public class JdkDynamicAopProxy implements InvocationHandler {
    private AdvisedSupport config;

    public JdkDynamicAopProxy(AdvisedSupport config) {
        this.config = config;
    }


    /**
     * InvocationHandler是Java中的一个接口，它定义了一个方法invoke(Object proxy, Method method, Object[] args)。
     * 它是Java动态代理机制的核心，用于在运行时生成代理类的实例，并将方法调用委托给代理类实例的处理程序。
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, Advice> advices = config.getAdvices(method,null);

        Object returnValue;
        try {
            invokeAdivce(advices.get("before"));

            returnValue = method.invoke(this.config.getTarget(),args);

            invokeAdivce(advices.get("after"));
        }catch (Exception e){
            invokeAdivce(advices.get("afterThrow"));
            throw e;
        }

        return returnValue;
    }

    private void invokeAdivce(Advice advice) {
        try {
            advice.getAdviceMethod().invoke(advice.getAspect());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回代理类
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), this.config.getTargetClass().getInterfaces(), this);
    }
}
