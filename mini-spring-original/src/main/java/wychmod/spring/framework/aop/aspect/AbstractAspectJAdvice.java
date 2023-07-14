package wychmod.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @description: 定义了拦截器的方法和属性，用于在目标对象的方法执行前、后或异常时执行相关操作
 * @author: wychmod
 * @date: 2023/6/30
 */
public abstract class AbstractAspectJAdvice implements Advice {

    private Object aspect;
    private Method adviceMethod;
    private String throwName;

    public AbstractAspectJAdvice(Object aspect, Method adviceMethod) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }

    /**
     * 执行切面的方法，将jp，返回值，异常信息传递给切面方法
     */
    protected Object invokeAdviceMethod(JoinPoint jp, Object returnValue, Throwable t) throws Throwable {

        //LogAspect.before(),LogAspect.after()  ...
        Class<?> [] paramTypes = this.adviceMethod.getParameterTypes();
        if(null == paramTypes || paramTypes.length == 0){
            return this.adviceMethod.invoke(this.aspect);
        }else{
            Object [] args = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i++) {
                if(paramTypes[i] == JoinPoint.class){
                    args[i] = jp;
                }else if(paramTypes[i] == Throwable.class){
                    args[i] = t;
                }else if(paramTypes[i] == Object.class){
                    args[i] = returnValue;
                }
            }
            return this.adviceMethod.invoke(aspect,args);
        }

    }
}
