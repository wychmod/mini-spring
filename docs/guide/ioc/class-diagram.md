---
title: IoC 全景类图
---

<DsDocHead title="IoC 全景类图" group="IoC 容器" :meta="['预计阅读 4 分钟', '涉及 16 个类']">

这张图把 iteration 模块按功能模块分组铺开，是全站的「地图」。每个面板都可以在对应章节找到逐类讲解。

</DsDocHead>

![Spring IoC 类图](/images/ioc-class-diagram.png)

## 继承树怎么读

核心是底部这条**实现链**，从左到右每一层只做一件事：

1. **`DefaultSingletonBeanRegistry`**：只管单例的存取——三个缓存 Map、`getSingleton` 查询、`registerSingleton` 登记
2. **`FactoryBeanRegistrySupport`**：在单例能力之上，为 `FactoryBean` 产物提供缓存
3. **`AbstractBeanFactory`**：定义 `doGetBean` 骨架，持有 `BeanPostProcessor` 列表与嵌入值解析器
4. **`AbstractAutowireCapableBeanFactory`**：实现 `createBean` 全流程（实例化 → 填充 → 初始化），是全站的主角
5. **`DefaultListableBeanFactory`**：集大成者——再加上 `BeanDefinitionRegistry`（定义注册）与 `ConfigurableListableBeanFactory`（组合接口），成为容器的默认实现

接口层则自上而下组合：`BeanFactory` 是根；`ListableBeanFactory` 加批量能力；`HierarchicalBeanFactory` 加父子层级；`AutowireCapableBeanFactory` 加自动装配；`ConfigurableBeanFactory` 组合层级与单例注册表；`ConfigurableListableBeanFactory` 把三者合一。

## 设计上的两个「为什么」

**为什么接口这么多？** 单一职责的接口让能力可以被单独引用——`ApplicationContext` 想要批量与层级，就 `extends ListableBeanFactory, HierarchicalBeanFactory`，而不必暴露 `createBean` 这类内部细节。

**为什么抽象类这么深？** 每一层抽象类只解决一个横切关注点（单例 / FactoryBean / 获取骨架 / 创建流程），`DefaultListableBeanFactory` 最后只做组装。这正是「分治 + 抽象」的教科书示范，也是读 Spring 真源码前最好的热身。

## 面板导航

| 面板 | 对应章节 |
| --- | --- |
| 资源加载 · Bean 定义与解析注册 | [容器启动全流程](/guide/ioc/bootstrap) |
| BeanFactory 体系 | 本章 |
| 容器扩展点 · 注解总览 | [Bean 生命周期](/guide/ioc/lifecycle) |
| 应用上下文 · 事件机制 | [事件监听与发布](/guide/advanced/events) |
| 实例化策略 · 类型转换 · FactoryBean | [Bean 生命周期](/guide/ioc/lifecycle) |
| 自动扫描 · Aware | [容器启动全流程](/guide/ioc/bootstrap) |
| 三级缓存 | [三级缓存解决循环依赖](/guide/ioc/circular-deps) |
