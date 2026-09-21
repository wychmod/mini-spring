---
title: 环境与运行
---

# 环境与运行

## 环境要求

- JDK 1.8
- Maven 3.x
- 一个顺手的 IDE（IDEA / Eclipse 均可）

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

## 跑通第一个用例

iteration 模块的测试目录里有一个完整示例：`spring.xml` 定义了 Bean 与依赖，`ApiTest#test_convert` 从容器中取出 Bean 并完成类型转换。

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

就这三行，背后已经走完了：定位配置 → 资源加载 → 解析注册 → refresh 启动 → 实例化 → 属性填充 → Aware 回调 → 初始化 → 事件发布。这正是接下来几章要拆开讲的东西。

## 一级缓存的极简演示

`CircleTest` 是一个不依赖框架的 `main` 方法，用 20 行代码演示循环依赖的本质——实例化后先把半成品放进缓存：

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
