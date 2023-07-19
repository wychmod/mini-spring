package com.wychmod.springframework.beans.factory;

/**
 * @description: 提供了对父子容器层次结构的支持。子容器可以重写父容器中定义的bean定义，而不是简单地覆盖它们。
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface HierarchicalBeanFactory extends BeanFactory{
}
