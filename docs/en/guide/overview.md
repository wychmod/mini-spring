---
title: "Overview: Two Paths"
---

# Overview: Two Paths

mini-spring is split into two modules. They teach the same set of Spring ideas at completely different paces.

## Module comparison

| | mini-spring-original | mini-spring-iteration |
| --- | --- | --- |
| Positioning | A minimal version in 30 classes | A progressive, source-code-style implementation |
| Naming | Independent, very short call chains | Same names and layering as Spring |
| Entry point | Annotation scanning + `DispatchServlet` MVC | `ClassPathXmlApplicationContext` + `refresh()` |
| AOP | CGLIB proxy + method interception chain | JDK / CGLIB dual proxy + Advisor auto-weaving |
| Circular dependencies | One-level cache demo (`CircleTest`) | Full three-level cache implementation |
| Best for | First contact, building intuition | Going deep after a first pass |

::: tip The main line of this site
Unless stated otherwise, every chapter on this site explains **mini-spring-iteration**. All class names, method names and call orders have been verified line-by-line against the repository source.
:::

## Feature panorama

The iteration module implements:

- **Container & definitions**: `BeanDefinition` registration, XML parsing (`XmlBeanDefinitionReader`), annotation scanning (`ClassPathBeanDefinitionScanner`)
- **Bootstrap**: the standard nine-step `AbstractApplicationContext#refresh()`
- **Full creation flow**: instantiation strategies, property population, Aware callbacks, init and destroy methods
- **Extension points**: `BeanFactoryPostProcessor`, `BeanPostProcessor`, `InstantiationAwareBeanPostProcessor`
- **Annotations**: `@Component` / `@Scope` / `@Autowired` / `@Value` / `@Qualifier`
- **AOP**: AspectJ pointcut expressions, before advice, an auto proxy creator
- **Events**: `ApplicationEventMulticaster` and container lifecycle events
- **Type conversion**: the `ConversionService` + `Converter` system
- **Circular dependencies**: three-level cache + early references (`getEarlyBeanReference`)

## How to read

- Just want the ideas → read [Container Bootstrap Flow](/en/guide/ioc/bootstrap) and [Bean Lifecycle](/en/guide/ioc/lifecycle) with the diagrams
- Want to get hands-on → follow [Setup & Run](/en/guide/getting-started) to run the test cases
- Want the details → [Three-Level Cache](/en/guide/ioc/circular-deps) and [AOP](/en/guide/aop) are the two most rewarding chapters
