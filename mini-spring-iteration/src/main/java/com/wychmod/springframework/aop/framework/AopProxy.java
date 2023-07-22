package com.wychmod.springframework.aop.framework;

/**
 * @description: AOP 代理的抽象
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface AopProxy {

    /**
     * 获取代理类
     */
    Object getProxy();

}