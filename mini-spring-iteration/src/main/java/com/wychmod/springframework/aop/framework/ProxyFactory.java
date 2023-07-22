package com.wychmod.springframework.aop.framework;

import com.wychmod.springframework.aop.AdvisedSupport;

/**
 * @description: 创建代理对象的工厂类。它的主要作用是简化代理对象的创建过程，并提供了一些便捷的方法和配置选项来定制代理对象的行为。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }

}