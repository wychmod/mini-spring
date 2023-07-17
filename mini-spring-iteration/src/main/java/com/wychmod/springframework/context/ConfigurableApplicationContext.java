package com.wychmod.springframework.context;

import com.wychmod.springframework.beans.BeansException;

/**
 * @description: 接口配置应用上下文
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

}
