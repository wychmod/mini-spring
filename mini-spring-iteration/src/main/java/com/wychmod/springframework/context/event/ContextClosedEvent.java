package com.wychmod.springframework.context.event;


/**
 * @description: 当应用程序上下文关闭时触发。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }

}
