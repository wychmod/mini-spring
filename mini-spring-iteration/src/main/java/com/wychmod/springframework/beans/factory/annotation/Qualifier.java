package com.wychmod.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @description: 按名称注入注解
 * @author: wychmod
 * @date: 2023/7/19
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
