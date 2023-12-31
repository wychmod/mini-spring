package com.wychmod.springframework.utils;

/**
 * @description: class工具类，用于加载类，获取类加载器，判断是否为基本类型等等
 * @author: wychmod
 * @date: 2023/7/14
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            // 获取当前线程的类加载器
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // 获取当前类的类加载器
            cl = ClassUtils.class.getClassLoader();
            if (cl == null) {
                // 获取系统类加载器
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }

    /**
     * 检查指定的类是否为 CGLIB 生成的类。
     * @param clazz the class to check
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * 检查指定的类名是否为 CGLIB 生成的类。
     * @param className the class name to check
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }


}
