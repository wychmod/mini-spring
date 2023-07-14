package com.wychmod.springframework.beans.factory.support;

import com.wychmod.springframework.beans.factory.BeansException;
import com.wychmod.springframework.beans.factory.config.BeanDefinition;

/**
 * @description: 实现默认bean创建的抽象bean工厂超类
 * @author: wychmod
 * @date: 2023/7/14
 */
public class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
