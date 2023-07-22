package com.wychmod.springframework.aop;

/**
 * @description: 一个特殊的 Advisor，只能用于定义切点和通知的组合。用于指定在哪些连接点上应用通知。
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取驱动这个通知的切入点
     */
    Pointcut getPointcut();

}
