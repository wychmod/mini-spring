package com.wychmod.spring.framework.aop.intercept;

/**
 * @description: 方法拦截器的接口,基类，通过实现该接口，可以实现对目标方法的前置、后置、异常、环绕等操作
 * 通过MethodInvocation的proceed()方法，可以不断调用拦截器链中的下一个拦截器，直到调用到目标方法为止
 * @author: wychmod
 * @date: 2023/6/30
 */
public interface MethodInterceptor {

    /**
     * @description: 方法拦截操作
     */
    Object invoke(MethodInvocation invocation) throws Throwable;

}
