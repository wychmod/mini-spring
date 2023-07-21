package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.context.ApplicationContext;
import com.wychmod.springframework.context.ApplicationContextAware;

/**
 * @description: 标记类接口，实现该接口可以被Spring容器感知
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface Aware {
}

// 感知例子

//public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
//
//    private ApplicationContext applicationContext;
//    private BeanFactory beanFactory;
//
//    private String uId;
//    private String company;
//    private String location;
//    private UserDao userDao;
//
//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.beanFactory = beanFactory;
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//    @Override
//    public void setBeanName(String name) {
//        System.out.println("Bean Name is：" + name);
//    }
//
//    @Override
//    public void setBeanClassLoader(ClassLoader classLoader) {
//        System.out.println("ClassLoader：" + classLoader);
//    }
//
//    // ...get/set
//}
//