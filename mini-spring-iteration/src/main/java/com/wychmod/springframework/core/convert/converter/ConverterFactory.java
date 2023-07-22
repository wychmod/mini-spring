package com.wychmod.springframework.core.convert.converter;

/**
 * @description: 类型转换工厂
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface ConverterFactory<S, R>{

    /**
     * Get the converter to convert from S to target type T, where T is also an instance of R.
     * @param <T> the target type
     * @param targetType the target type to convert to
     * @return a converter from S to T
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
