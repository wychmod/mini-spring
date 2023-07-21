package com.wychmod.springframework.aop;

import com.wychmod.springframework.utils.ClassUtils;

/**
 * @description: 为代理对象提供目标对象的访问。
 * Spring AOP 需要通过代理对象来实现横切关注点的功能，代理对象通常需要通过 TargetSource 来获取目标对象。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 获取目标对象实现的接口列表
     */
    public Class<?>[] getTargetClass(){
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    /**
     * 获取目标对象
     */
    public Object getTarget(){
        return this.target;
    }
    
}
