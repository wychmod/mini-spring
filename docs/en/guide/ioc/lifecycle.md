---
title: Bean Lifecycle
---

<DsDocHead title="Bean Lifecycle" group="IoC Container" :meta="['5 min read', '23 classes']">

At the end of the previous chapter, `preInstantiateSingletons()` handed control over to `createBean`. This chapter unpacks the main flow of `AbstractAutowireCapableBeanFactory`.

</DsDocHead>

![Bean lifecycle](/images/bean-lifecycle.png)

## The main flow: doCreateBean

```java
protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
    // 1. instantiate the bean
    bean = createBeanInstance(beanDefinition, beanName, args);
    // 2. expose an early factory for singletons (circular-dependency groundwork)
    if (beanDefinition.isSingleton()) {
        addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, beanDefinition, finalBean));
    }
    // 3. post-instantiation check (returning false skips property population)
    boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);
    // 4. before applying values, let BeanPostProcessors modify them (@Autowired kicks in here)
    applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
    // 5. populate properties
    applyPropertyValues(beanName, bean, beanDefinition);
    // 6. Aware callbacks + before/after initialization + init methods
    bean = initializeBean(beanName, bean, beanDefinition);
    // 7. register the destroy callback
    registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
    // 8. for singletons, fetch the exposed object from cache and register it
    if (beanDefinition.isSingleton()) {
        exposedObject = getSingleton(beanName);
        registerSingleton(beanName, exposedObject);
    }
    return exposedObject;
}
```

## Instantiation: a strategy pattern

`createBeanInstance` only picks a constructor; actual creation is delegated to `InstantiationStrategy`:

- `SimpleInstantiationStrategy`: JDK reflection via `Constructor.newInstance`
- `CglibSubclassingInstantiationStrategy`: method-level CGLIB enhancement

The former is the default; swap it any time via `setInstantiationStrategy`.

## Property population: three routes

`applyPropertyValues` walks the `PropertyValues`; each `PropertyValue` takes one of three routes depending on its value:

```java
if (value instanceof BeanReference) {
    // A depends on B: a recursive getBean triggers B's creation
    value = getBean(beanReference.getBeanName());
} else {
    // non-reference values: ConversionService type conversion (@Value strings)
    if (conversionService.canConvert(sourceType, targetType)) {
        value = conversionService.convert(value, targetType);
    }
}
// reflective write
BeanUtil.setFieldValue(bean, name, value);
```

`@Autowired` / `@Value` / `@Qualifier` are handled by `AutowiredAnnotationBeanPostProcessor#postProcessPropertyValues` in step 4, which appends parsed annotations into `PropertyValues` — merging into the same pipeline as XML configuration.

## Aware callbacks and initialization

`initializeBean` follows a fixed order:

1. **Aware callbacks**: `BeanFactoryAware` → `BeanClassLoaderAware` → `BeanNameAware` (`ApplicationContextAware` is delivered by the `ApplicationContextAwareProcessor` registered back in bootstrap step 3)
2. **Before-init**: `BeanPostProcessor#postProcessBeforeInitialization`
3. **Init methods**: `InitializingBean#afterPropertiesSet` or a custom `init-method` — **they are mutually exclusive**; implementing the interface skips the configured method to avoid double execution
4. **After-init**: `BeanPostProcessor#postProcessAfterInitialization` — **this is where the AOP proxy is created**

::: warning A difference from common interview lore
Many materials claim "when both are provided, the interface method runs first, then the configured init-method". In this implementation, `invokeInitMethods` makes them explicitly exclusive:

```java
if (StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean)) { ... }
```

Trust the code.
:::

## Destruction

When the container `close()`es, `destroySingletons()` destroys singletons in **reverse registration order**. Per-bean destruction goes through the `DisposableBeanAdapter`: first `DisposableBean#destroy`, then the custom `destroy-method` via reflection (skipped when the method has the same name — again guarding against double execution).

Non-singleton beans never register destroy callbacks — a prototype's lifecycle belongs to its caller.
