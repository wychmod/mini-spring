package com.wychmod.spring.framework.aop.aspect;

import com.wychmod.spring.framework.aop.intercept.MethodInterceptor;
import com.wychmod.spring.framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @description: 后置通知拦截器
 * @author: wychmod
 * @date: 2023/6/30
 */
public class AfterReturningAdviceInterceptor extends AbstractAspectJAdvice implements MethodInterceptor {

    private JoinPoint jp;

    public AfterReturningAdviceInterceptor(Object aspect, Method adviceMethod) {
        super(aspect, adviceMethod);
    }

    public void afterReturning(Object returnValue, Method method, Object[] args,Object target) throws Throwable {
        invokeAdviceMethod(this.jp, returnValue, null);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        this.jp = mi;
        Object returnValue = mi.proceed();
        this.afterReturning(returnValue,mi.getMethod(),mi.getArguments(),mi.getThis());
        return returnValue;
    }
}