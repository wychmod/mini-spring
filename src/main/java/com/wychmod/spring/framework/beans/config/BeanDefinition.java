package com.wychmod.spring.framework.beans.config;

/**
 * @description: BeanDefinition 用来存储Bean中的配置信息
 * @author: wychmod
 * @date: 2023/6/23
 */
public class BeanDefinition {
    /**
     * 首字母小写的bean名称/自定义/接口的名称
     */
    private String factoryBeanName;

    /**
     * 全类名
     */
    private String beanClassName;

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}
