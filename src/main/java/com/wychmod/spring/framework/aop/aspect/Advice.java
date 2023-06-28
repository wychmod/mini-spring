package com.wychmod.spring.framework.aop.aspect;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @description: 定义了拦截器的方法和属性，用于在目标对象的方法执行前、后或异常时执行相关操作
 * @author: wychmod
 * @date: 2023/6/28
 */
@Data
public class Advice {
    private Object aspect;
    private Method adviceMethod;
    private String throwName;

    public Advice(Object aspect, Method adviceMethod) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }

}
