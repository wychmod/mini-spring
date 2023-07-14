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
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }


    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

}
