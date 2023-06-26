package com.wychmod.spring.framework.context;

import com.wychmod.spring.framework.annotation.Autowired;
import com.wychmod.spring.framework.annotation.Controller;
import com.wychmod.spring.framework.annotation.Service;
import com.wychmod.spring.framework.beans.BeanWrapper;
import com.wychmod.spring.framework.beans.config.BeanDefinition;
import com.wychmod.spring.framework.beans.support.BeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 职责：完成bean的创建和DI（依赖注入）的功能，即IOC的功能
 * @author: wychmod
 * @date: 2023/6/23
 */
public class ApplicationContext {


    private BeanDefinitionReader reader;

    /**
     * 根据对象名找到该对象的配置信息，为生成BeanWrapper做准备
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    /**
     * Ioc容器,装着包装类
     */
    private Map<String, BeanWrapper> factoryBeanInstanceCache = new HashMap<String, BeanWrapper>();

    /**
     * Object容器，装着本身的类
     */
    private Map<String,Object> factoryBeanObjectCache = new HashMap<String, Object>();

    public ApplicationContext(String... configLocations) {

        // 1. 加载配置文件
        reader = new BeanDefinitionReader(configLocations);

        try {
            // 2. 解析配置文件，封装成BeanDefinition
            List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
            
            // 3. 把BeanDefinition缓存起来
            doRegistBeanDefinition(beanDefinitions);
            
            // 4. DI
            doAutowrited();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doAutowrited() {
        // 调用getBean(),这一步，所有的Bean并没有真正的实例化，还只是配置阶段
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            getBean(beanName);
        }

    }

    private void doRegistBeanDefinition(List<BeanDefinition> beanDefinitions) throws Exception {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            // 判断是否有相同名称的bean被注册，注册则抛出异常
            if (this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + "is exists");
            }
            
            beanDefinitionMap.put(beanDefinition.getBeanClassName(), beanDefinition);
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    
    }

    /**
     * Bean的实例化，DI从这个方法开始
     * @param beanName
     * @return BeanWrapper
     */
    public Object getBean(String beanName) {
        //1、先拿到BeanDefinition配置信息
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        //2、反射实例化newInstance();
        Object instance = instantiateBean(beanName,beanDefinition);
        //3、封装成一个叫做BeanWrapper
        BeanWrapper beanWrapper = new BeanWrapper(instance);
        //4、保存到IoC容器
        factoryBeanInstanceCache.put(beanName,beanWrapper);
        //5、执行依赖注入
        populateBean(beanName,beanDefinition,beanWrapper);

        return beanWrapper.getWrapperInstance();
    }

    private void populateBean(String beanName, BeanDefinition beanDefinition, BeanWrapper beanWrapper) {
        // 涉及循环依赖问题
        Object instance = beanWrapper.getWrapperInstance();
        Class<?> clazz = beanWrapper.getWrappedClass();

        //在Spring中@Component
        if(!(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class))){
            return;
        }

        for (Field field : clazz.getDeclaredFields()) {
            if(!field.isAnnotationPresent(Autowired.class)){ continue; }

            Autowired autowired = field.getAnnotation(Autowired.class);

            //如果用户没有自定义的beanName，就默认根据类型注入
            String autowiredBeanName = autowired.value().trim();
            if("".equals(autowiredBeanName)){
                //field.getType().getName() 获取字段的类型
                autowiredBeanName = field.getType().getName();
            }

            //暴力访问
            field.setAccessible(true);

            try {
                if(this.factoryBeanInstanceCache.get(autowiredBeanName) == null){
                    continue;
                }
                //ioc.get(beanName) 相当于通过接口的全名拿到接口的实现的实例
                field.set(instance,this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
        }


    }

    /**
     * 创建真正的实例对象
     * @param beanName
     * @param beanDefinition
     * @return instance
     */
    private Object instantiateBean(String beanName, BeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();
        Object instance = null;
        try {
            if (this.factoryBeanObjectCache.containsKey(beanName)){
                instance = this.factoryBeanInstanceCache.get(beanName);
            }else {
                Class<?> clazz = Class.forName(className);

                // 默认类名首字母小写
                instance = clazz.newInstance();
                this.factoryBeanObjectCache.put(beanName, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Object getBean(Class<?> beanClass) {
        return getBean(beanClass.getName());
    }
}
