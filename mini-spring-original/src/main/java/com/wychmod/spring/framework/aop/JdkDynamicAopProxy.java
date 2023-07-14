package com.wychmod.spring.framework.aop;

import com.wychmod.spring.framework.aop.intercept.MethodInvocation;
import com.wychmod.spring.framework.aop.support.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @description: 代理对象,InvocationHandler是Java中的一个接口，它定义了一个方法invoke(Object proxy, Method method, Object[] args)。
 * 它是Java动态代理机制的核心，用于在运行时生成代理类的实例，并将方法调用委托给代理类实例的处理程序。
 * @author: wychmod
 * @date: 2023/6/28
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport config) {
        this.advisedSupport = config;
    }


    /**
     * 代理对象的方法调用,通过返回拦截器链,然后调用拦截器链的proceed方法, proceed()串联起了整个Interceptor调用链
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> chain = this.advisedSupport.getInterceptorsAndDynamicInterceptionAdvice(method, this.advisedSupport.getTargetClass());

        MethodInvocation invocation = new MethodInvocation(proxy, this.advisedSupport.getTarget(), method, args, this.advisedSupport.getTargetClass(), chain);

        return invocation.proceed();
    }

    /**
     * 返回代理类
     * @return
     */
    public Object getProxy() {
        return this.getProxy(this.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advisedSupport.getTargetClass().getInterfaces(), this);
    }
}
