package com.wychmod.springframework.beans.factory.support;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.core.io.Resource;
import com.wychmod.springframework.core.io.ResourceLoader;

/**
 * @description: Simple interface for bean definition readers.
 * getRegistry()、getResourceLoader()，都是用于提供给后面三个方法的工具，加载和注册，这两个方法的实现会包装到抽象类中，以免污染具体的接口实现方法。
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
