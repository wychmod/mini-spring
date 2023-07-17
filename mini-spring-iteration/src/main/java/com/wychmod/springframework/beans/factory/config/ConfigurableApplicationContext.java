package com.wychmod.springframework.beans.factory.config;

import com.wychmod.springframework.beans.BeansException;

/**
 * @description: 定义注册虚拟机钩子的方法 registerShutdownHook 和手动执行关闭的方法 close。
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface ConfigurableApplicationContext {

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
