package com.wychmod.springframework.beans;

/**
 * @description: Bean 属性信息
 * @author: wychmod
 * @date: 2023/7/14
 */
public class PropertyValue {

    /** 属性名称 */
    private final String name;

    /** 属性值 */
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
