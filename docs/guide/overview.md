---
title: 项目总览：两条实现路径
---

# 项目总览：两条实现路径

mini-spring 分为两个模块，讲的是同一套 Spring 思想，但节奏完全不同。

## 模块对照

| | mini-spring-original | mini-spring-iteration |
| --- | --- | --- |
| 定位 | 30 个类的极简版 | 源码风格的渐进式实现 |
| 类命名 | 独立命名，链路极短 | 与 Spring 源码同名同分层 |
| 入口 | 注解扫描 + `DispatchServlet` 的 MVC | `ClassPathXmlApplicationContext` + `refresh()` |
| 配置方式 | `@Component` 等注解扫描 | XML（`XmlBeanDefinitionReader`）+ 注解扫描 |
| AOP | JDK / CGLIB 双代理 + 方法拦截链 | JDK / CGLIB 双代理 + Advisor 自动织入 |
| 循环依赖 | 一级缓存演示（`CircleTest`） | 完整三级缓存实现 |
| 适合谁 | 第一次接触，建立直觉 | 读过一遍后，深入每个细节 |

::: tip 本文档的主线
除特别说明外，本站所有章节都以 **mini-spring-iteration** 为准讲解，所有类名、方法名、调用顺序均与仓库源码逐一核对过。
:::

## 功能全景

iteration 模块实现了这些能力：

- **容器与定义**：`BeanDefinition` 注册、XML 解析（`XmlBeanDefinitionReader`）、注解扫描（`ClassPathBeanDefinitionScanner`）
- **启动流程**：`AbstractApplicationContext#refresh()` 九步标准启动
- **创建全程**：实例化策略、属性填充、Aware 回调、初始化方法、销毁回调
- **扩展点**：`BeanFactoryPostProcessor`、`BeanPostProcessor`、`InstantiationAwareBeanPostProcessor`
- **注解支持**：`@Component` / `@Scope` / `@Autowired` / `@Value` / `@Qualifier`
- **AOP**：AspectJ 切点表达式、前置通知、自动代理创建器
- **事件**：`ApplicationEventMulticaster`、容器生命周期事件
- **类型转换**：`ConversionService` + `Converter` 体系
- **循环依赖**：三级缓存 + 提前引用（`getEarlyBeanReference`）

## 怎么读

- 只想看懂思路 → 先读 [容器启动全流程](/guide/ioc/bootstrap) 和 [Bean 生命周期](/guide/ioc/lifecycle)，配合图即可
- 想动手 → 跟着 [快速开始](/guide/getting-started) 把测试用例跑起来
- 想抠细节 → [三级缓存](/guide/ioc/circular-deps) 和 [AOP](/guide/aop) 是含金量最高的两章
