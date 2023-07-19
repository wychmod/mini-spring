package com.wychmod.springframework.beans.factory.config;

/**
 * @description: 单例注册表
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

}
