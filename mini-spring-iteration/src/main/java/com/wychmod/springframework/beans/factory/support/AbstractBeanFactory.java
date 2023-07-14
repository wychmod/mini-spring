package com.wychmod.springframework.beans.factory.support;

import com.wychmod.springframework.beans.factory.BeanFactory;
import com.wychmod.springframework.beans.factory.BeansException;
import com.wychmod.springframework.beans.factory.config.BeanDefinition;

/**
 * @description: 抽象的 Bean 工厂基类，定义模板方法
 * 继承了 DefaultSingletonBeanRegistry，也就具备了使用单例注册类方法的能力。
 *
 * @author: wychmod
 * @date: 2023/7/14
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
