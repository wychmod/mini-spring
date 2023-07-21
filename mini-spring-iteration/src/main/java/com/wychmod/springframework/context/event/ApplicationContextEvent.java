package com.wychmod.springframework.context.event;

import com.wychmod.springframework.context.ApplicationContext;
import com.wychmod.springframework.context.ApplicationEvent;

/**
 * @description: 应用程序上下文相关的事件
 * 监听应用程序上下文的生命周期事件。当应用程序上下文发生特定的事件时，会触发相应的 ApplicationContextEvent，并将其发送给注册的监听器。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取引发事件的应用程序上下文。
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
