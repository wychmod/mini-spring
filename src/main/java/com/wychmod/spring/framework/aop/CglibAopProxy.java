package com.wychmod.spring.framework.aop;

import com.wychmod.spring.framework.aop.support.AdvisedSupport;

/**
 * @description: cglib动态代理实现类，咱未实现
 * @author: wychmod
 * @date: 2023/6/30
 */
public class CglibAopProxy implements AopProxy{
    public CglibAopProxy(AdvisedSupport config) {
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
