package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;

/**
 * @description: 动态地创建和获取对象实例
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
