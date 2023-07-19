package com.wychmod.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 属性值集合
 * @author: wychmod
 * @date: 2023/7/14
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加属性值
     * @param pv 属性值
     */
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**
     * 获取属性值列表
     * @return 属性值列表
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
