package com.wychmod.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * @description: 类型转换抽象接口
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface ConversionService {

    /** 如果 {@code sourceType} 的对象可以转换为 {@code targetType}，则返回 {@code true}。 */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /** 将给定的 {@code 源} 转换为指定的 {@code 目标类型}。 */
    <T> T convert(Object source, Class<T> targetType);

}
