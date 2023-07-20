package com.wychmod.springframework.utils;

/**
 * @description: 将包含占位符的字符串解析为实际的值。实现类可以使用任何策略来解析值，例如从属性文件中查找值，或者使用系统属性或环境属性等。默认实现使用Spring的占位符替换器功能。
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface StringValueResolver {

    String resolveStringValue(String strVal);

}

