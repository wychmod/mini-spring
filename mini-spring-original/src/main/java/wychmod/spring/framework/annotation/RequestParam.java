package wychmod.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @description: 请求参数映射
 * @author: wychmod
 * @date: 2023/6/23
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";

    boolean required() default true;
}
