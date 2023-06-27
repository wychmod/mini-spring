package com.wychmod.spring.framework.webmvc.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @description: Url 和 对应 Method的映射
 * @author: wychmod
 * @date: 2023/6/27
 */
public class HandlerMapping {
    /**
     * URL 正则对象方便处理
     */
    private Pattern pattern;

    /**
     * URL 对应方法
     */
    private Method method;

    /**
     * Method的实例对象
     */
    private Object controller;

    public HandlerMapping(Pattern pattern, Method method, Object controller) {
        this.pattern = pattern;
        this.method = method;
        this.controller = controller;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
