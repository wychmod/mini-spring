package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.wychmod.springframework.beans.factory.config.BeanDefinition;
import com.wychmod.springframework.beans.factory.config.BeanPostProcessor;
import com.wychmod.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @description: 提供分析和修改Bean以及预先实例化的操作接口
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface ConfigurableListableBeanFactory  extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化单例Bean
     */
    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
