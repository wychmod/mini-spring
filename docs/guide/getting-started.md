---
title: 环境与运行
---

<DsDocHead title="环境与运行" group="快速开始" :meta="['预计阅读 6 分钟', '涉及 2 个类']">

从克隆仓库到跑通第一个测试用例，这一页把环境要求、目录结构与常见报错一次说清。

</DsDocHead>

## 环境要求

| 项 | 要求 | 说明 |
| --- | --- | --- |
| JDK | 1.8 及以上 | 已在 8 / 11 / 17 上验证 |
| Maven | 3.6 及以上 | 用于构建与跑测试 |
| IDE | IntelliJ IDEA / Eclipse | 需开启注解处理（Lombok 依赖它） |

## 获取代码

```bash
git clone https://github.com/wychmod/mini-spring.git
cd mini-spring
```

仓库是一个 Maven 聚合工程，包含两个模块：

```xml
<modules>
    <module>mini-spring-iteration</module>
    <module>mini-spring-original</module>
</modules>
```

构建两个模块（跳过测试，先确认编译通过）：

```bash
mvn -q clean test-compile
```

## 目录结构

<pre class="ds-tree"><span class="root">mini-spring/</span>
<span class="conn">├─</span> mini-spring-original/     29 个类的极简版（注解扫描）
<span class="conn">├─</span> mini-spring-iteration/    98 个类，源码风格的渐进式实现
<span class="conn">├─</span> design/                   重构方案与线框规范
<span class="conn">└─</span> docs/                     VitePress 文档站（本站，含架构图资源）
</pre>

每个包负责什么，见[目录结构导览](/guide/start/structure)。

## 跑通第一个用例

iteration 模块的测试目录里有一个完整示例：`ApiTest#test_convert` 从容器中取出 Bean 并打印结果。

```bash
mvn test -pl mini-spring-iteration -Dtest=ApiTest
```

测试的核心逻辑只有三行：

```java
ClassPathXmlApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("classpath:spring.xml");
Husband husband = applicationContext.getBean("husband", Husband.class);
System.out.println("测试结果：" + husband);
```

::: warning 注意：仓库中的 spring.xml 当前是注释状态
`src/main/resources/spring.xml` 在仓库里作为**样例文件**保留，内容整体被注释，直接运行上面的测试会因容器中没有 Bean 定义而失败。跑通它只需在 `spring.xml` 里写入最小配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans>
    <bean id="husband" class="com.wychmod.springframework.test.bean.Husband"/>
</beans>
```

这三行背后已经走完了：定位配置 → 资源加载 → 解析注册 → refresh 启动 → 实例化 → 属性填充 → Aware 回调 → 初始化 → 事件发布。这正是接下来几章要拆开讲的东西。
:::

## 一级缓存的极简演示

`CircleTest` 是一个不依赖任何配置的 `main` 方法（**开箱即跑**），用 20 行代码演示循环依赖的本质——实例化后先把半成品放进缓存：

```java
// 实例化对象入缓存
Object obj = beanClass.newInstance();
singletonObjects.put(beanName, obj);
// 属性填充补全对象
for (Field field : fields) {
    field.set(obj, singletonObjects.containsKey(fieldBeanName)
            ? singletonObjects.get(fieldBeanName) : getBean(fieldClass));
}
```

```bash
mvn test-compile -pl mini-spring-iteration
# 然后在 IDE 中直接运行 CircleTest#main
```

它就是三级缓存的「思想原型」，读它之后再进入[三级缓存](/guide/ioc/circular-deps)章节会非常顺。

## 遇到报错？

<div class="ds-grid ds-grid--2">
<article class="ds-card"><span class="tag">ERRORS</span><h3>常见报错排查</h3><p>8 条高频问题的现象、原因与解决，支持关键词搜索。</p><a class="ds-card-link" :href="$href('/guide/start/troubleshooting')">打开排查表 →</a></article>
<article class="ds-card"><span class="tag">FAQ</span><h3>常见问题</h3><p>「为什么三级缓存要存工厂」这类原理困惑，在 FAQ 里集中回答。</p><a class="ds-card-link" :href="$href('/faq')">查看 FAQ →</a></article>
</div>
