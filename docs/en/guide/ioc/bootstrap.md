---
title: Container Bootstrap Flow
---

# Container Bootstrap Flow

What happens between `new ClassPathXmlApplicationContext("classpath:spring.xml")` and beans being usable? This chapter walks the real call chain.

![Class loading flow](/images/class-loading-flow.png)

## Step 1: Locate the configuration

```java
new ClassPathXmlApplicationContext("classpath:spring.xml")
```

- `ClassPathXmlApplicationContext` extends `AbstractXmlApplicationContext`; config locations come from `getConfigLocations()`
- The whole context family extends `DefaultResourceLoader`, so resolving resources by prefix (`classpath:` / `file:` / URL) comes for free

## Step 2: Load and parse resources

`AbstractRefreshableApplicationContext` declares `loadBeanDefinitions` as abstract; for XML it is implemented by the subclass `AbstractXmlApplicationContext`, which creates an `XmlBeanDefinitionReader` and hands the config locations over:

- `loadBeanDefinitions(location)` → the `ResourceLoader` produces a `Resource` (`ClassPathResource` / `FileSystemResource` / `UrlResource`)
- `doLoadBeanDefinitions(InputStream)` parses the XML with SAX:
  - `<bean>` attributes: `class` / `scope` / `init-method` / `destroy-method`
  - `<property>` values: `value` / `value-ref` → wrapped as `PropertyValue`
  - `<component-scan>` → triggers package scanning via `scanPackage`

## Step 3: Register BeanDefinitions

Parsing results are wrapped into a `BeanDefinition` (beanClass, PropertyValues, scope, init/destroy-method) and written into the `beanDefinitionMap` of `DefaultListableBeanFactory`.

The annotation path converges on the same structure: `@Component` + `@Scope` beans are scanned by `ClassPathBeanDefinitionScanner#doScan` and registered as identical `BeanDefinition`s — everything downstream is shared.

## Step 4: The nine steps of refresh()

```java
public void refresh() {
    // 1. create the BeanFactory and load BeanDefinitions
    refreshBeanFactory();
    // 2. obtain the BeanFactory
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    // 3. add the ApplicationContextAwareProcessor
    beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    // 4. invoke BeanFactoryPostProcessors before any instantiation
    invokeBeanFactoryPostProcessors(beanFactory);
    // 5. register BeanPostProcessors
    registerBeanPostProcessors(beanFactory);
    // 6. initialize the event multicaster
    initApplicationEventMulticaster();
    // 7. register event listeners
    registerListeners();
    // 8. set the conversion service, pre-instantiate singletons
    finishBeanFactoryInitialization(beanFactory);
    // 9. publish the refresh-complete event
    finishRefresh();
}
```

Three things worth noticing:

1. **Step 4 runs before step 5**: `BeanFactoryPostProcessor`s (like `PropertyPlaceholderConfigurer` resolving `${}` placeholders) modify BeanDefinitions, so they must run before any bean is instantiated
2. **Step 5 must register early**: `BeanPostProcessor`s are beans themselves; only after registration can they intercept the creation of every bean that follows
3. **Step 8 is where beans are actually created**: `preInstantiateSingletons()` triggers `getBean → doGetBean → createBean` for every singleton definition — entering the [Bean Lifecycle](/en/guide/ioc/lifecycle)

## Closing down

- `finishRefresh()` publishes `ContextRefreshedEvent` — the container is ready
- `close()` publishes `ContextClosedEvent` first, then calls `destroySingletons()` to destroy singletons in reverse registration order; `registerShutdownHook()` hooks this onto the JVM shutdown hook
