package com.wychmod.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @description: 通知类基本接口
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface Advisor {

    /**
     * 获取通知
     */
    Advice getAdvice();

}
