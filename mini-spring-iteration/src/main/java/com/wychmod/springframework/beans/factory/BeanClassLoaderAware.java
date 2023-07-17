package com.wychmod.springframework.beans.factory;

/**
 * @description: 实现此接口，能感知到所属的 ClassLoader
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);
}
