package com.wychmod.springframework.beans.factory.config;

/**
 * @description: 单例注册表接口，具有注册和获取单例对象的功能。
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface SingletonBeanRegistry {

    /**
     * 返回在给定名称下注册的（原始）单例对象。
     * @param beanName 要查找的bean的名称
     * @return 返回注册的单例对象
     */
    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

}
