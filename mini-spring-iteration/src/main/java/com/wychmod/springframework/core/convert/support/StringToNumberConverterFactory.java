package com.wychmod.springframework.core.convert.support;

import com.wychmod.springframework.core.convert.converter.Converter;
import com.wychmod.springframework.core.convert.converter.ConverterFactory;
import com.sun.istack.internal.Nullable;
import com.wychmod.springframework.utils.NumberUtils;


/**
 * @description: 从字符串转换任何 JDK 标准数字实现。
 * @author: wychmod
 * @date: 2023/7/19
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }

}
