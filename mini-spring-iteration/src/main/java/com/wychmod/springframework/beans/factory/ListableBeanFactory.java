package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.BeanFactory;

import java.util.Map;

/**
 * @description: 将由bean工厂实现的{@linkBeanFactory}接口的扩展
 *可以枚举其所有bean实例，而不是尝试bean查找
 *根据客户的要求，按名称逐一列出。BeanFactory实现
 *预加载它们的所有bean定义（例如基于XML的工厂）可以实现
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
