package com.wychmod.springframework.aop;

import java.lang.reflect.Method;

/**
 * @description: 定义类过滤器的规范，它的作用是用于过滤需要处理的方法。
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface MethodMatcher {

    /**
     * 判断表达式匹配范围内是否存在给定的方法
     */
    boolean matches(Method method, Class<?> targetClass);
    
}
