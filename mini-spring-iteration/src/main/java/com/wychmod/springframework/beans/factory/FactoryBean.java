package com.wychmod.springframework.beans.factory;

/**
 * @description: 作用是充当工厂Bean，用于创建和管理其他Bean的实例。
 * @author: wychmod
 * @date: 2023/7/19
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}

// 使用案例
//public class CustomBeanFactory implements FactoryBean<CustomBean> {
//
//    @Override
//    public CustomBean getObject() throws Exception {
//        // 在这里进行复杂的Bean实例化逻辑
//        CustomBean customBean = new CustomBean();
//        customBean.setName("Custom Bean");
//        // 可以进行一些自定义的初始化操作
//        customBean.init();
//        return customBean;
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return CustomBean.class;
//    }
//
//    @Override
//    public boolean isSingleton() {
//        return true;
//    }
//}
// 配置xml
//<bean id="customBeanFactory" class="com.example.CustomBeanFactory" />
//<bean id="customBean" factory-bean="customBeanFactory" factory-method="getObject" />