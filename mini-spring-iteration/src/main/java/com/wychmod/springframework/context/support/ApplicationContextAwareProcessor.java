package com.wychmod.springframework.context.support;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.ApplicationContextAware;
import com.wychmod.springframework.beans.factory.config.BeanPostProcessor;
import com.wychmod.springframework.context.ApplicationContext;

/**
 * @description: 通过 BeanPostProcessor 实现类感知应用上下文对象
 * @author: wychmod
 * @date: 2023/7/17
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
