package com.wychmod.springframework.context.support;

import com.wychmod.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.wychmod.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @description: 抽象基类 XML 上下文
 * @author: wychmod
 * @date: 2023/7/17
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
