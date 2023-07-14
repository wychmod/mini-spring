package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.config.BeanDefinition;

/**
 * @description: 提供分析和修改Bean以及预先实例化的操作接口
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface ConfigurableListableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
