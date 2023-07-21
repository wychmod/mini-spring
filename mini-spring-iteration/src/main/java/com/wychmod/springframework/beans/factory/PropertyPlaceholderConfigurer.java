package com.wychmod.springframework.beans.factory;

import com.wychmod.springframework.beans.BeansException;
import com.wychmod.springframework.beans.PropertyValue;
import com.wychmod.springframework.beans.PropertyValues;
import com.wychmod.springframework.beans.factory.config.BeanDefinition;
import com.wychmod.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.wychmod.springframework.core.io.DefaultResourceLoader;
import com.wychmod.springframework.core.io.Resource;
import com.wychmod.springframework.utils.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @description: 用于解析和替换属性占位符
 * @author: wychmod
 * @date: 2023/7/19
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * 默认占位符前缀: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * 默认占位符后缀: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            // 加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);

            // 占位符替换属性值
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
                }
            }

            // 向容器中添加字符串解析器，供解析@Value注解使用
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);

        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    /**
     * 解析占位符
     */
    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }

    }

}
