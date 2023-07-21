package com.wychmod.springframework.context.event;

/**
 * @description: 当应用程序上下文刷新时触发，即在应用程序上下文初始化完成后触发。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(Object source) {
        super(source);
    }

}
