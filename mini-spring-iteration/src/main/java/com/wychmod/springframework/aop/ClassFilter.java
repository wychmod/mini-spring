package com.wychmod.springframework.aop;

/**
 * @description: 定义类过滤器的规范，它的作用是用于过滤需要处理的类。
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface ClassFilter {

    /**
     * 判断表达式匹配范围内是否存在给定的类、接口
     */
    boolean matches(Class<?> clazz);

}
