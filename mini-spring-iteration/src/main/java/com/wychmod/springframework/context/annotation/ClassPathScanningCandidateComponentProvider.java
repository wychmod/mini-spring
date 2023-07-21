package com.wychmod.springframework.context.annotation;

import com.wychmod.springframework.beans.factory.config.BeanDefinition;
import com.wychmod.springframework.stereotype.Component;
import cn.hutool.core.util.ClassUtil;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @description: 用于扫描指定路径下的Components并识别成为候选的组件
 * @author: wychmod
 * @date: 2023/7/19
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 扫描获取指定包下的所有Components
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
