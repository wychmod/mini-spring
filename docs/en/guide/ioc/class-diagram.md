---
title: IoC Class Diagram
---

<DsDocHead title="IoC Class Diagram" group="IoC Container" :meta="['4 min read', '18 classes']">

This diagram lays out the iteration module grouped by functional area — the "map" for the whole site. Each panel is explained chapter by chapter elsewhere.

</DsDocHead>

![IoC class diagram](/images/ioc-class-diagram.png)

## How to read the inheritance tree

The core is the **implementation chain** at the bottom; each layer to the right adds exactly one concern:

1. **`DefaultSingletonBeanRegistry`**: only manages singleton storage — the three cache Maps, `getSingleton` lookup, `registerSingleton` registration
2. **`FactoryBeanRegistrySupport`**: on top of singleton storage, adds caching for `FactoryBean` products
3. **`AbstractBeanFactory`**: defines the `doGetBean` skeleton, holds the `BeanPostProcessor` list and embedded value resolver
4. **`AbstractAutowireCapableBeanFactory`**: implements the full `createBean` flow (instantiate → populate → initialize) — the protagonist of this site
5. **`DefaultListableBeanFactory`**: the synthesis — adds `BeanDefinitionRegistry` (definition registration) and `ConfigurableListableBeanFactory` (composite interface), becoming the default container implementation

The interface layers compose from the top down: `BeanFactory` is the root; `ListableBeanFactory` adds batch access; `HierarchicalBeanFactory` adds parent-child hierarchy; `AutowireCapableBeanFactory` adds autowiring; `ConfigurableBeanFactory` combines hierarchy with the singleton registry; `ConfigurableListableBeanFactory` fuses all three.

## Two "whys" behind the design

**Why so many interfaces?** Focused interfaces let capabilities be referenced individually — `ApplicationContext` wants batch and hierarchy, so it `extends ListableBeanFactory, HierarchicalBeanFactory` without exposing internals like `createBean`.

**Why so many abstract classes?** Each layer solves exactly one cross-cutting concern (singletons / FactoryBean / the lookup skeleton / the creation flow), and `DefaultListableBeanFactory` merely assembles them. It is a textbook demonstration of divide-and-conquer and abstraction — the best warm-up before reading real Spring source.

## Panel directory

| Panel | Chapter |
| --- | --- |
| Resource loading · Definition parsing & registration | [Container Bootstrap Flow](/en/guide/ioc/bootstrap) |
| BeanFactory system | This chapter |
| Extension points · Annotation overview | [Bean Lifecycle](/en/guide/ioc/lifecycle) |
| Application context · Events | [Events](/en/guide/advanced/events) |
| Instantiation strategies · Conversion · FactoryBean | [Bean Lifecycle](/en/guide/ioc/lifecycle) |
| Component scanning · Aware | [Container Bootstrap Flow](/en/guide/ioc/bootstrap) |
| Three-level cache | [Three-Level Cache](/en/guide/ioc/circular-deps) |
