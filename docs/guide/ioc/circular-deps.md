---
title: 三级缓存解决循环依赖
---

# 三级缓存解决循环依赖

A 依赖 B，B 又依赖 A——创建 A 时要等 B，创建 B 时又要等 A，死锁。Spring 的答案是把「实例化完成但未填充属性」的半成品提前暴露出去，mini-spring 完整复刻了这个设计。

![三级缓存解决循环依赖](/images/three-level-cache.png)

## 三个缓存都在哪

`DefaultSingletonBeanRegistry` 持有三个 Map：

```java
/** 一级缓存，普通对象（成品） */
private Map<String, Object> singletonObjects = new HashMap<>();

/** 二级缓存，提前暴漏对象，没有完全实例化的对象 */
protected final Map<String, Object> earlySingletonObjects = new HashMap<>();

/** 三级缓存，存放代理对象（工厂） */
private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();
```

## 查询顺序：一级 → 二级 → 三级

```java
public Object getSingleton(String beanName) {
    Object singletonObject = singletonObjects.get(beanName);
    if (null == singletonObject) {
        singletonObject = earlySingletonObjects.get(beanName);
        if (null == singletonObject) {
            ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
            if (singletonFactory != null) {
                singletonObject = singletonFactory.getObject();
                // 把三级缓存中的代理对象获取出来，放入二级缓存中
                earlySingletonObjects.put(beanName, singletonObject);
                singletonFactories.remove(beanName);
            }
        }
    }
    return singletonObject;
}
```

三级命中后**立即升级**：结果放入二级缓存、工厂从三级删除——工厂只允许被消费一次。

## A / B 的完整时序

以 A 的创建为主线：

1. **A 实例化**：`createBeanInstance` 反射创建原始对象，属性还是空的
2. **A 暴露工厂**：`addSingletonFactory("a", () -> getEarlyBeanReference(...))` 进入三级缓存
3. **填充属性发现依赖 B**：触发 `getBean("b")`，B 走同样流程
4. **B 实例化并暴露工厂**，填充属性发现依赖 A → `getSingleton("a")`
5. **三级命中**：调用 A 的工厂 → `getEarlyBeanReference(A)`（若 A 需要 AOP，此刻生成代理）→ 结果进二级缓存、工厂删出三级
6. **B 拿到 A 的早期引用**，B 完成初始化，`registerSingleton("b")` 进入一级缓存
7. **A 恢复创建**：完成初始化后 `getSingleton("a")` 从二级缓存取回早期引用（保证全程拿到的是同一个对象），`registerSingleton("a")` 进入一级缓存，清空二三级

::: tip 和生命周期章节的呼应
第 2 步发生在 [Bean 生命周期](/guide/ioc/lifecycle) 的「暴露早期引用」节点；第 7 步对应主流程末尾的 `getSingleton(beanName) + registerSingleton(beanName, exposedObject)`。
:::

## 为什么是三级，而不是二级

二级缓存也能解决裸的循环依赖（`CircleTest` 就是一级缓存的演示），三级缓存的增值在 **AOP 代理的延迟决策**：

- 三级缓存存的是 **`ObjectFactory` 工厂**，不是对象本身
- 只有**真正发生循环依赖**、工厂被调用时，`getEarlyBeanReference` 才会走 `DefaultAdvisorAutoProxyCreator` 决定是否提前生成代理
- 没有循环依赖时，代理在初始化后的 `postProcessAfterInitialization` 正常生成——**绝大多数 Bean 永远不会提前代理**

如果直接用二级缓存存提前引用，要么所有 Bean 都被迫提前代理，要么在 AOP 场景下拿不到一致的对象。三级缓存用「工厂」把这两难拆开了。

::: warning 边界
只有**单例** Bean 参与缓存流转；prototype 作用域不缓存，Spring（含本项目）不解决 prototype 的循环依赖。
:::
