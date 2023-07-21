package com.wychmod.springframework.aop;

/**
 * @description: Pointcut（切点）用于定义在哪些方法或者类上应用切面（Aspect）的逻辑
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface Pointcut {

    /**
     * 返回此切入点的类筛选器。
     */
    ClassFilter getClassFilter();

    /**
     * 返回此切入点的 MethodMatcher。
     */
    MethodMatcher getMethodMatcher();

}
