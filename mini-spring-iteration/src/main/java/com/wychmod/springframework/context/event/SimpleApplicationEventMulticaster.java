package com.wychmod.springframework.context.event;

import com.wychmod.springframework.beans.factory.BeanFactory;
import com.wychmod.springframework.context.ApplicationEvent;
import com.wychmod.springframework.context.ApplicationListener;

/**
 * @description: 简单实现AbstractApplicationEventMulticaster
 * @author: wychmod
 * @date: 2023/7/19
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
