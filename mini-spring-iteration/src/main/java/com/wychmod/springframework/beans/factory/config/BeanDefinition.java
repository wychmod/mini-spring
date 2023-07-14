package com.wychmod.springframework.beans.factory.config;

/**
 * @description: BeanDefinition 用来存储Bean中的配置信息
 * @author: wychmod
 * @date: 2023/7/14
 */
public class BeanDefinition {
    private Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
