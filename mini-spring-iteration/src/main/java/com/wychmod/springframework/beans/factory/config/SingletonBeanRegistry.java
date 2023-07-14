package com.wychmod.springframework.beans.factory.config;

/**
 * @description: 定义了一个获取单例对象的接口。
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
