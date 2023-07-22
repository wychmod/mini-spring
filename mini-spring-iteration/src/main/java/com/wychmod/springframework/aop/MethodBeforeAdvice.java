package com.wychmod.springframework.aop;

import java.lang.reflect.Method;

/**
 * @description: 定义方法级别的前置通知，即在目标方法执行之前执行的通知。
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    /**
     * 在方法调用之前回调通知
     */
    void before(Method method, Object[] args, Object target) throws Throwable;

}
