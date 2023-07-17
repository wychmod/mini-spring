package com.wychmod.springframework.context;

import com.wychmod.springframework.beans.factory.ListableBeanFactory;

/**
 * @description: 应用上下文接口 为应用程序提供配置的中央接口。它维护一个 BeanFactory，提供配置文件的基本功能。
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface ApplicationContext extends ListableBeanFactory {

}
