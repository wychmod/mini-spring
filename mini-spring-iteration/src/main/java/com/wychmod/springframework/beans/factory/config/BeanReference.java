package com.wychmod.springframework.beans.factory.config;

/**
 * @description: Bean 引用, 用于表示bean之间的依赖关系, 例如xml中一个bean依赖于另一个bean
 * @author: wychmod
 * @date: 2023/7/14
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
