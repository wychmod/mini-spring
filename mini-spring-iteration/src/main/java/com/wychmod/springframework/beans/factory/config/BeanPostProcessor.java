package com.wychmod.springframework.beans.factory.config;

import com.wychmod.springframework.beans.BeansException;

/**
 * @description: 用于修改新实例化 Bean 对象的扩展点
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
