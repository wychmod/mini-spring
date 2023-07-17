package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.context.ApplicationContext;

/**
 * @description: 实现此接口，能感知到所属的 ApplicationContext
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface ApplicationContextAware extends Aware{

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
