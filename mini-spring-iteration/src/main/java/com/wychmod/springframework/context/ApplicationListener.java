package com.wychmod.springframework.context;

import java.util.EventListener;

/**
 * @description: 由应用程序事件侦听器实现的接口。基于观察器设计模式。
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 事件处理
     */
    void onApplicationEvent(E event);

}