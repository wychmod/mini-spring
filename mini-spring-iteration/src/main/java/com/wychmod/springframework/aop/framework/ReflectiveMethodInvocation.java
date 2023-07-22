package com.wychmod.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @description: 实现方法的拦截和增强。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    // 目标对象
    protected final Object target;
    // 方法
    protected final Method method;
    // 入参
    protected final Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    /**
     * getStaticPart() 方法用于获取当前方法调用的静态部分，即代理对象中被调用的方法本身。在这段代码中，返回的是 method 对象，它是一个 AccessibleObject 类型的对象。
     *
     * AccessibleObject 是 Java 反射 API 中的一个抽象类，它是 Field、Method 和 Constructor 类的父类。它提供了一些方法，用于设置和获取这些成员对象的可访问性。
     *
     * 在这里，返回的 AccessibleObject 对象实际上就是被代理对象中被调用的方法本身。通过返回这个对象，可以获取到方法的相关信息，如方法名、参数类型等，从而对方法进行进一步的处理和分析。
     */
    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }

}