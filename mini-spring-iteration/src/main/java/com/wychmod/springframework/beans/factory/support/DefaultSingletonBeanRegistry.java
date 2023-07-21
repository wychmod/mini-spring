package com.wychmod.springframework.beans.factory.support;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.factory.DisposableBean;
import com.wychmod.springframework.beans.factory.ObjectFactory;
import com.wychmod.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 提供了对单例Bean的注册、获取和管理的功能，是Spring容器中单例Bean的注册表。处理循环依赖
 * @author: wychmod
 * @date: 2023/7/14
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 空单例对象的内部标记：用作并发映射（不支持空值）的标记值。
     */
    protected static final Object NULL_OBJECT = new Object();

    /**
     * 一级缓存，普通对象
     * 单例对象的缓存：Bean 名称 --> Bean 实例
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 二级缓存，提前暴漏对象，没有完全实例化的对象
     * 早期单例对象的缓存：Bean 名称 --> Bean 实例
     */
    protected final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>();

    /**
     * 三级缓存，存放代理对象
     * 单例工厂的缓存：Bean name --> 对象工厂
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        // 一级缓存中是否有对象，有的话直接返回
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            // 判断二级缓存中是否有对象，这个对象就是代理对象，因为只有代理对象才会放到三级缓存中
            if (null == singletonObject) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
