---
title: 容器启动全流程
---

# 容器启动全流程

一个 `new ClassPathXmlApplicationContext("classpath:spring.xml")` 到 Bean 可用之间发生了什么？这一章沿真实调用链走一遍。

![Spring 类加载全流程](/images/class-loading-flow.png)

## 第一步：定位配置

```java
new ClassPathXmlApplicationContext("classpath:spring.xml")
```

- `ClassPathXmlApplicationContext` 继承自 `AbstractXmlApplicationContext`，配置地址由 `getConfigLocations()` 提供
- 整个 context 体系继承 `DefaultResourceLoader`，所以天然拥有按前缀（`classpath:` / `file:` / URL）解析资源的能力

## 第二步：资源加载与解析

`AbstractRefreshableApplicationContext#loadBeanDefinitions` 把加载定义的工作交给 `XmlBeanDefinitionReader`：

- `loadBeanDefinitions(location)` → `ResourceLoader` 拿到 `Resource`（`ClassPathResource` / `FileSystemResource` / `UrlResource`）
- `doLoadBeanDefinitions(InputStream)` 用 SAX 解析 XML：
  - `<bean>` 的 `class` / `scope` / `init-method` / `destroy-method`
  - `<property>` 的 `value` / `value-ref` → 包装成 `PropertyValue`
  - `<component-scan>` → 触发 `scanPackage` 包扫描

## 第三步：注册 BeanDefinition

解析结果被封装为 `BeanDefinition`（beanClass、PropertyValues、scope、init/destroy-method），写入 `DefaultListableBeanFactory` 的 `beanDefinitionMap`。

注解路径殊途同归：`@Component` + `@Scope` 由 `ClassPathBeanDefinitionScanner#doScan` 扫描后注册为同样的 `BeanDefinition`，后续流程完全一致。

## 第四步：refresh() 九步

```java
public void refresh() {
    // 1. 创建 BeanFactory，并加载 BeanDefinition
    refreshBeanFactory();
    // 2. 获取 BeanFactory
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    // 3. 添加 ApplicationContextAwareProcessor
    beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    // 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor
    invokeBeanFactoryPostProcessors(beanFactory);
    // 5. 注册 BeanPostProcessor
    registerBeanPostProcessors(beanFactory);
    // 6. 初始化事件广播器
    initApplicationEventMulticaster();
    // 7. 注册事件监听器
    registerListeners();
    // 8. 设置类型转换器、提前实例化单例
    finishBeanFactoryInitialization(beanFactory);
    // 9. 发布容器刷新完成事件
    finishRefresh();
}
```

三个值得注意的点：

1. **第 4 步先于第 5 步**：`BeanFactoryPostProcessor`（如 `PropertyPlaceholderConfigurer` 替换 `${}` 占位符）修改的是 BeanDefinition，必须发生在任何 Bean 实例化之前
2. **第 5 步要提前注册**：`BeanPostProcessor` 本身也是 Bean，只有先注册，后续 Bean 的创建过程才能被它们介入
3. **第 8 步才是真正的 Bean 创建**：`preInstantiateSingletons()` 对所有单例定义触发 `getBean → doGetBean → createBean`，进入 [Bean 生命周期](/guide/ioc/lifecycle)

## 收尾与关闭

- `finishRefresh()` 发布 `ContextRefreshedEvent`，容器就绪
- `close()` 先发布 `ContextClosedEvent`，再调用 `destroySingletons()` 按注册逆序销毁单例；`registerShutdownHook()` 把这一切挂到 JVM 钩子上
