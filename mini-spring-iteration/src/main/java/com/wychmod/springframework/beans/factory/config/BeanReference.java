package com.wychmod.springframework.beans.factory.config;

/**
 * @description: Bean 引用
 * @author: wychmod
 * @date: 2023/7/14
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
