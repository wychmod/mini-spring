package com.wychmod.springframework.core.convert.converter;

/**
 * @description: 类型转换处理接口
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface Converter<S, T>  {

    /** 将类型 {@code S} 的源对象转换为目标类型 {@code T}。 */
    T convert(S source);

}
