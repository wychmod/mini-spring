---
title: Bean 生命周期
---

<DsDocHead title="Bean 生命周期" group="IoC 容器" :meta="['预计阅读 5 分钟', '涉及 23 个类']">

上一章末尾，`preInstantiateSingletons()` 把球踢给了 `createBean`。这一章拆开 `AbstractAutowireCapableBeanFactory` 的主流程。

</DsDocHead>

![Bean 生命周期](/images/bean-lifecycle.png)

## 主流程 doCreateBean

```java
protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
    // 1. 实例化 Bean
    bean = createBeanInstance(beanDefinition, beanName, args);
    // 2. 单例 Bean 提前暴露工厂（循环依赖伏笔）
    if (beanDefinition.isSingleton()) {
        addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, beanDefinition, finalBean));
    }
    // 3. 实例化后判断（返回 false 则跳过属性填充）
    boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);
    // 4. 属性填充前，允许 BeanPostProcessor 修改属性值（@Autowired 在此生效）
    applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
    // 5. 给 Bean 填充属性
    applyPropertyValues(beanName, bean, beanDefinition);
    // 6. Aware 回调 + 初始化前后处理 + 初始化方法
    bean = initializeBean(beanName, bean, beanDefinition);
    // 7. 注册销毁回调
    registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
    // 8. 单例从缓存取最终暴露对象，并登记一级缓存
    if (beanDefinition.isSingleton()) {
        exposedObject = getSingleton(beanName);
        registerSingleton(beanName, exposedObject);
    }
    return exposedObject;
}
```

## 实例化：策略模式

`createBeanInstance` 只负责找构造器，真正的创建交给 `InstantiationStrategy`：

- `SimpleInstantiationStrategy`：JDK 反射 `Constructor.newInstance`
- `CglibSubclassingInstantiationStrategy`：CGLIB 方法级增强

默认前者，可随时 `setInstantiationStrategy` 替换。

## 属性填充：三条路

`applyPropertyValues` 遍历 `PropertyValues`，每个 `PropertyValue` 按值类型分三条路：

```java
if (value instanceof BeanReference) {
    // A 依赖 B：递归 getBean 触发 B 的创建
    value = getBean(beanReference.getBeanName());
} else {
    // 非引用值：ConversionService 类型转换（@Value 字符串）
    if (conversionService.canConvert(sourceType, targetType)) {
        value = conversionService.convert(value, targetType);
    }
}
// 反射写入
BeanUtil.setFieldValue(bean, name, value);
```

`@Autowired` / `@Value` / `@Qualifier` 则由 `AutowiredAnnotationBeanPostProcessor#postProcessPropertyValues` 在第 4 步把注解解析结果追加进 `PropertyValues`，与 XML 配置在同一管道汇合。

## Aware 回调与初始化

`initializeBean` 的顺序固定：

1. **Aware 回调**：`BeanFactoryAware` → `BeanClassLoaderAware` → `BeanNameAware`（`ApplicationContextAware` 由第 3 步注册的 `ApplicationContextAwareProcessor` 在前置处理阶段传入上下文）
2. **前置处理**：`BeanPostProcessor#postProcessBeforeInitialization`
3. **初始化方法**：`InitializingBean#afterPropertiesSet` 或自定义 `init-method`——**两者互斥**，实现了接口就跳过配置方法，避免二次执行
4. **后置处理**：`BeanPostProcessor#postProcessAfterInitialization`，**AOP 代理就在这里生成**

::: warning 与常见八股的差异
不少资料说「接口方法与 init-method 同时提供时先执行接口再执行配置方法」。在本项目的实现里，`invokeInitMethods` 明确做了互斥判断：

```java
if (StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean)) { ... }
```

以代码为准。
:::

## 销毁

容器 `close()` 时 `destroySingletons()` 按**注册逆序**逐个销毁。单个 Bean 的销毁由 `DisposableBeanAdapter` 适配：先 `DisposableBean#destroy`，再反射调用自定义 `destroy-method`（方法同名则跳过，同样防二次执行）。

非单例 Bean 不注册销毁回调——prototype 的生命周期归调用方管理。
