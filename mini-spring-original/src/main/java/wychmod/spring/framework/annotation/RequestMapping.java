package wychmod.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @description: 请求Url
 * @author: wychmod
 * @date: 2023/6/23
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
