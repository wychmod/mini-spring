package com.wychmod.springframework.context;

import java.util.EventObject;

/**
 * @description: 事件机制的核心组件之一。它用于实现发布-订阅模式，允许在应用程序中进行事件的发送和监听。
 * @author: wychmod
 * @date: 2023/7/19
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }

}
