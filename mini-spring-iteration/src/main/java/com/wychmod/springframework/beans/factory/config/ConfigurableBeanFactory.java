package com.wychmod.springframework.beans.factory.config;

import com.wychmod.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @description: 处理配置的bean工厂的接口，例如注册beanPostProcessor，销毁单例对象等等
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry{

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    /**
     * 添加字符串解析器
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * 解析给定字符串，如注释属性
     */
    String resolveEmbeddedValue(String value);
}
