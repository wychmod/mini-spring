package com.wychmod.springframework.beans.factory.config;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @description: 允许自定义修改 BeanDefinition 属性信息
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
