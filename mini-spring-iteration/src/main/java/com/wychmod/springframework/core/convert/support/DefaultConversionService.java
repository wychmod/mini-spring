package com.wychmod.springframework.core.convert.support;

import com.wychmod.springframework.core.convert.converter.ConverterRegistry;

/**
 * @description: 通用转换服务，默认配置适用于大多数环境的转换器。.
 * @author: wychmod
 * @date: 2023/7/19
 */
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }

}
