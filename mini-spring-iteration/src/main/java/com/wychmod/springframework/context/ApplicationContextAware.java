package com.wychmod.springframework.context;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.Aware;


/**
 * @description: 实现此接口，既能感知到所属的 ApplicationContext
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
