package com.wychmod.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @description: 业务层接口
 * @author: wychmod
 * @date: 2023/6/23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
