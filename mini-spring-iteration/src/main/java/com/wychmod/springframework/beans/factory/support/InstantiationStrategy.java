package com.wychmod.springframework.beans.factory.support;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @description: Bean 实例化策略接口
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
