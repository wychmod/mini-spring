---
title: Three-Level Cache & Circular Dependencies
---

# Three-Level Cache & Circular Dependencies

A depends on B, and B depends on A — creating A waits for B, creating B waits for A: deadlock. Spring's answer is to expose the *instantiated-but-not-yet-populated* half-product early, and mini-spring replicates that design completely.

![Three-level cache](/images/three-level-cache.png)

## Where the three caches live

`DefaultSingletonBeanRegistry` holds three Maps:

```java
/** Level 1: finished singletons */
private Map<String, Object> singletonObjects = new HashMap<>();

/** Level 2: exposed early references, not fully initialized */
protected final Map<String, Object> earlySingletonObjects = new HashMap<>();

/** Level 3: singleton factories (proxies) */
private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();
```

## Lookup order: level 1 → 2 → 3

```java
public Object getSingleton(String beanName) {
    Object singletonObject = singletonObjects.get(beanName);
    if (null == singletonObject) {
        singletonObject = earlySingletonObjects.get(beanName);
        if (null == singletonObject) {
            ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
            if (singletonFactory != null) {
                singletonObject = singletonFactory.getObject();
                // move the (possibly proxied) object up to the level-2 cache
                earlySingletonObjects.put(beanName, singletonObject);
                singletonFactories.remove(beanName);
            }
        }
    }
    return singletonObject;
}
```

A level-3 hit **promotes immediately**: the result goes into the level-2 cache and the factory is removed — a factory is consumed exactly once.

## The full A/B sequence

With A's creation as the main line:

1. **A is instantiated**: `createBeanInstance` reflects the raw object; properties are still empty
2. **A exposes a factory**: `addSingletonFactory("a", () -> getEarlyBeanReference(...))` enters the level-3 cache
3. **Population discovers B**: `getBean("b")` fires, and B goes through the same flow
4. **B is instantiated and exposes its factory**; while populating, B discovers A → `getSingleton("a")`
5. **Level-3 hit**: A's factory is invoked → `getEarlyBeanReference(A)` (creating the proxy now if A needs AOP) → result into level 2, factory removed from level 3
6. **B gets A's early reference**, finishes initialization, and `registerSingleton("b")` enters the level-1 cache
7. **A resumes**: after finishing initialization, `getSingleton("a")` fetches the early reference from level 2 (guaranteeing everyone sees the same object), then `registerSingleton("a")` enters level 1 and levels 2/3 are cleared

::: tip Ties back to the lifecycle chapter
Step 2 happens at the "expose early reference" node of the [Bean Lifecycle](/en/guide/ioc/lifecycle); step 7 corresponds to the final `getSingleton(beanName) + registerSingleton(beanName, exposedObject)` in the main flow.
:::

## Why three levels, not two

A two-level cache already solves bare circular dependencies (`CircleTest` is exactly that with one). The third level adds **deferred decision for AOP proxies**:

- Level 3 stores an **`ObjectFactory`**, not an object
- Only when a circular dependency *actually happens* and the factory is invoked does `getEarlyBeanReference` go through `DefaultAdvisorAutoProxyCreator` to decide whether to create the proxy early
- Without circular dependencies, proxies are created normally in `postProcessAfterInitialization` after initialization — **the vast majority of beans are never proxied early**

With only two levels you must either proxy every bean early, or risk inconsistent objects under AOP. The factory-based third level splits that dilemma apart.

::: warning Boundary
Only **singleton** beans participate in cache flow; prototypes are not cached, and neither Spring nor this project resolves prototype circular dependencies.
:::
