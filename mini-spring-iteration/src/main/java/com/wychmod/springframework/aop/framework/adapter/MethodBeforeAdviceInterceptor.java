package com.wychmod.springframework.aop.framework.adapter;

import com.wychmod.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description: 实现方法级别的前置通知（Before advice）。
 * 主要作用是在目标方法执行之前执行一些预处理逻辑。它可以在目标方法被调用之前，插入自定义的逻辑代码。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }

}