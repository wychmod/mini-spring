package wychmod.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @description: 控制层注解标识类
 * @author: wychmod
 * @date: 2023/6/23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
