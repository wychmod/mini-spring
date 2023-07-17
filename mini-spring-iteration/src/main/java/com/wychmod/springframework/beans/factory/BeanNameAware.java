package com.wychmod.springframework.beans.factory;

/**
 * @description: 实现此接口，能感知到所属的 BeanName
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface BeanNameAware extends Aware {

    /**
     * 设置 Bean 的名称
     *
     * @param name
     */
    void setBeanName(String name);
}

