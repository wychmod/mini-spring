package com.wychmod.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @description: 自动注入
 * @author: wychmod
 * @date: 2023/6/23
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value() default "";
}
