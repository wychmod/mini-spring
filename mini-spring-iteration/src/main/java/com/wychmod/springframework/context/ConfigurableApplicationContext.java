package com.wychmod.springframework.context;

import com.wychmod.springframework.beans.BeansException;

/**
 * @description: 提供了对应用程序上下文的配置和管理的能力，使得应用程序可以更加灵活地进行配置和定制。
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh() throws BeansException;

    void publishEvent(ApplicationEvent event);

    /**
     * 注册一个虚拟机钩子，关闭容器
     */
    void registerShutdownHook();

    /**
     * 关闭容器
     */
    void close();
}
