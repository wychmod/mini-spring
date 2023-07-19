package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.BeanFactory;

import java.util.Map;

/**
 * @description: ListableBeanFactory接口是BeanFactory的子接口，它扩展了BeanFactory的功能，提供了对容器中所有Bean的列表操作和查询的能力。
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
