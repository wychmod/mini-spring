package com.wychmod.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @description: 值注解
 * @author: wychmod
 * @date: 2023/7/19
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    /**
     * 实际值表达式：例如“{systemProperties.myProp}”。
     */
    String value();

}
