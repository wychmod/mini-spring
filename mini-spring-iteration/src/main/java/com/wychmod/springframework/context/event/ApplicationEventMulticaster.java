package com.wychmod.springframework.context.event;

import com.wychmod.springframework.context.ApplicationEvent;
import com.wychmod.springframework.context.ApplicationListener;

/**
 * @description: 事件广播器, 管理和分发应用程序事件的机制
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加侦听器以接收所有事件的通知。@param侦听器，要添加的侦听器
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 从通知列表中删除侦听器。@param侦听器，要删除的侦听器
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 发布事件到多个侦听器。@param事件，要发布的事件
     */
    void multicastEvent(ApplicationEvent event);

}
