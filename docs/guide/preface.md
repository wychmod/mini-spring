---
title: 为什么手写 Spring
---

# 为什么手写 Spring

Spring 是 Java 后端工程师绕不开的框架，但「会用」和「懂」之间隔着一整座源码的大山。手写一个迷你版 Spring，是翻越这座山性价比最高的方式。

## 三个理由

**1. 把「背概念」变成「讲得清」**

IoC、DI、AOP、循环依赖……这些词面试必问，但只背八股很容易在一层追问下露馅。当你亲手实现过三级缓存、亲手用 `BeanPostProcessor` 织入代理，任何深度的追问都有代码可以依托。

**2. 源码阅读的脚手架**

Spring 源码有几十万行，直接阅读很容易迷路。mini-spring 的 `mini-spring-iteration` 模块刻意保持与 Spring 相同的类名和分层（`AbstractAutowireCapableBeanFactory`、`DefaultSingletonBeanRegistry`、`AbstractApplicationContext#refresh`），读完手写版再去看真源码，等于拿着地图进森林。

**3. 造轮子的完整闭环**

从解析 XML 到反射实例化，从属性填充到事件广播，你会把反射、设计模式、并发容器这些散落的知识点串成一条完整的线——这是读任何教程都给不了的体感。

## 本站的讲法

- 每个章节都配一张与源码逐一核对过的**架构图或流程图**
- 关键逻辑直接贴出 mini-spring 的真实代码，再解释**为什么 Spring 这样设计**
- 不堆术语，按「问题 → 方案 → 代价」的顺序推进

准备好了就从下一页的[项目总览](/guide/overview)开始。
