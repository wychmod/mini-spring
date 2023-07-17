package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;

/**
 * @description: 实现此接口，能感知到所属的 BeanFactory
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
