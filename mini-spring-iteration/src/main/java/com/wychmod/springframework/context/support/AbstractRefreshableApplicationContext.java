package com.wychmod.springframework.context.support;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.wychmod.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @description: 抽象基类刷新应用上下文
 * @author: wychmod
 * @date: 2023/7/17
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
