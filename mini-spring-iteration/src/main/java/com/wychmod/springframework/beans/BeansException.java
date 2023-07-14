package com.wychmod.springframework.beans;

/**
 * @description: 定义 Bean 异常
 * @author: wychmod
 * @date: 2023/7/14
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
