package com.wychmod.spring.framework.aop;

/**
 * @description: Aop代理接口，提供获取代理对象的方法，用于后续的方法调用
 * @author: wychmod
 * @date: 2023/6/30
 */
public interface AopProxy {

    Object getProxy();


    Object getProxy(ClassLoader classLoader);
}
