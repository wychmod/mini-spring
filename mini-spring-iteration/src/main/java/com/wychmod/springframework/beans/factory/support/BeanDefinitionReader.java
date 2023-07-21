package com.wychmod.springframework.beans.factory.support;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.core.io.Resource;
import com.wychmod.springframework.core.io.ResourceLoader;

/**
 * @description: BeanDefinition的读取接口.
 * getRegistry()、getResourceLoader()，都是用于提供给后面三个方法的工具，加载和注册，这两个方法的实现会包装到抽象类中，以免污染具体的接口实现方法。
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface BeanDefinitionReader {

    /** 获取bean配置注册接口 */
    BeanDefinitionRegistry getRegistry();

    /** 获取资源加载器 */
    ResourceLoader getResourceLoader();

    /** 加载单个Bean定义 */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
