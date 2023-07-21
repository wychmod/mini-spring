package com.wychmod.springframework.context;

/**
 * @description: 事件发布者接口, ApplicationContext实现该接口，具有发布事件的能力
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface ApplicationEventPublisher {

    /**
     * 通知在此应用程序中注册的所有侦听器应用程序事件。
     */
    void publishEvent(ApplicationEvent event);

}
