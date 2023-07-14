package wychmod.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @description: 切点接口参数
 * @author: wychmod
 * @date: 2023/6/30
 */
public interface JoinPoint {
    Method getMethod();

    Object[] getArguments();

    Object getThis();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
