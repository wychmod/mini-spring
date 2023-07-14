package wychmod.spring.framework.beans.support;

import com.wychmod.spring.framework.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @description: BeanDefinitionReader 用来读取配置文件，将配置文件中的信息转换成BeanDefinition对象，存储到容器中，供后续使用
 * @author: wychmod
 * @date: 2023/6/23
 */
public class BeanDefinitionReader {

    /**
     * 缓存所有的类名，享元模式
     */
    private List<String> regitryBeanClasses = new ArrayList<String>();


    /**
     * Properties类，读取和写入属性文件的工具类
     */
    private Properties contextConfig = new Properties();

    public BeanDefinitionReader(String... configLocations) {

        // 1. 加载配置文件
        doLoadConfig(configLocations[0]);
        // 2. 扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
    }

    public Properties getConfig() {
        return this.contextConfig;
    }

    private void doScanner(String scanPackage) {
        // 找到资源的完整路径
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());

        //当成是一个ClassPath文件夹下的所有文件，递归的扫描每一个文件
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                // 不是class文件直接跳过
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                // 全类名 = 包名.类名
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                regitryBeanClasses.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replaceAll("classpath:",""));
        try {
            contextConfig.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<BeanDefinition> loadBeanDefinitions() {
        List<BeanDefinition> result = new ArrayList<BeanDefinition>();
        
        try {
            for (String className : regitryBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if(beanClass.isInterface()){continue;}
                // 保存类对应的ClassName（全类名）和 beanName
                // 1. 默认是类名首字母小写
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
                // 2. 自定义

                // 3. 接口注入
                for (Class<?> beanClassInterface : beanClass.getInterfaces()) {
                    result.add(doCreateBeanDefinition(beanClassInterface.getName(),beanClass.getName()));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return result;
    }

    /**
     * 创建BeanDefinition
     * @param beanName 首字母小写的bean名称/自定义/接口的名称
     * @param beanClassName 全类名
     */
    private BeanDefinition doCreateBeanDefinition(String beanName, String beanClassName) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setFactoryBeanName(beanName);
        beanDefinition.setBeanClassName(beanClassName);
        return beanDefinition;
    }

    /**
     * 将类名首字母小写
     */
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
