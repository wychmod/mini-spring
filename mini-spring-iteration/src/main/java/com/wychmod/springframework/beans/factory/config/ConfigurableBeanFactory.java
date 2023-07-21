package com.wychmod.springframework.beans.factory.config;

import com.sun.istack.internal.Nullable;
import com.wychmod.springframework.beans.factory.HierarchicalBeanFactory;
import com.wychmod.springframework.core.convert.ConversionService;
import com.wychmod.springframework.utils.StringValueResolver;

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

    /**
     * 指定用于转换属性值的Spring 3.0转换服务，作为JavaBeans PropertyEditors的替代方法。
     */
    void setConversionService(ConversionService conversionService);

    /**
     * 返回关联的转换服务（如果有）。
     */
    @Nullable
    ConversionService getConversionService();
}
