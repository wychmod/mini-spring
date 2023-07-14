package com.wychmod.springframework.beans.factory.support;

import com.wychmod.springframework.beans.factory.config.BeanDefinition;

/**
 * @description: Bean 定义注册接口
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName       Bean 名称
     * @param beanDefinition Bean 定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
