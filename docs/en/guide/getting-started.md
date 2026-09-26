---
title: Setup & Run
---

<DsDocHead title="Setup &amp; Run" group="Getting Started" :meta="['6 min read', '2 classes']">

From cloning the repo to running your first test — requirements, project layout and common
pitfalls in one page.

</DsDocHead>

## Requirements

| Item | Requirement | Notes |
| --- | --- | --- |
| JDK | 1.8 or newer | Verified on 8 / 11 / 17 |
| Maven | 3.6 or newer | Used to build and run tests |
| IDE | IntelliJ IDEA / Eclipse | Annotation processing must be enabled (Lombok needs it) |

## Get the code

```bash
git clone https://github.com/wychmod/mini-spring.git
cd mini-spring
```

The repository is a Maven multi-module project:

```xml
<modules>
    <module>mini-spring-iteration</module>
    <module>mini-spring-original</module>
</modules>
```

Build both modules, skipping tests, to confirm everything compiles:

```bash
mvn -q clean test-compile
```

## Project layout

<pre class="ds-tree"><span class="root">mini-spring/</span>
<span class="conn">├─</span> mini-spring-original/     29-class minimal version (annotation scanning)
<span class="conn">├─</span> mini-spring-iteration/    98 classes, progressive, source-code-style
<span class="conn">├─</span> design/                   Redesign spec and wireframes
<span class="conn">└─</span> docs/                     VitePress documentation site (this site, with diagrams)
</pre>

What each package is responsible for is covered in [Project Structure](/en/guide/start/structure).

## Run your first test case

The iteration module's test directory contains a complete example: `ApiTest#test_convert` pulls a bean out of the container and prints it.

```bash
mvn test -pl mini-spring-iteration -Dtest=ApiTest
```

The core of the test is three lines:

```java
ClassPathXmlApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("classpath:spring.xml");
Husband husband = applicationContext.getBean("husband", Husband.class);
System.out.println("Result: " + husband);
```

::: warning Note: spring.xml in the repo is currently commented out
`src/main/resources/spring.xml` is kept in the repository as a **sample file** with its content commented out, so the test above fails with an empty container as-is. To run it, write a minimal configuration into `spring.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans>
    <bean id="husband" class="com.wychmod.springframework.test.bean.Husband"/>
</beans>
```

Behind these three lines the container has already: located the config → loaded resources → parsed and registered definitions → run the refresh bootstrap → instantiated → populated properties → invoked Aware callbacks → initialized → published events. That is exactly what the next chapters unpack.
:::

## The one-level-cache demo

`CircleTest` is a plain `main` method with no configuration needed (**runs out of the box**) — about 20 lines showing the essence of circular dependencies: put the half-built instance into a cache right after instantiation.

```java
// instantiate and cache
Object obj = beanClass.newInstance();
singletonObjects.put(beanName, obj);
// populate properties to complete the object
for (Field field : fields) {
    field.set(obj, singletonObjects.containsKey(fieldBeanName)
            ? singletonObjects.get(fieldBeanName) : getBean(fieldClass));
}
```

```bash
mvn test-compile -pl mini-spring-iteration
# then run CircleTest#main from your IDE
```

It is the *idea prototype* of the three-level cache — read it and the [Three-Level Cache](/en/guide/ioc/circular-deps) chapter will feel natural.

## Hit an error?

<div class="ds-grid ds-grid--2">
<article class="ds-card"><span class="tag">ERRORS</span><h3>Troubleshooting</h3><p>Eight common issues with symptom, cause and fix — searchable.</p><a class="ds-card-link" :href="$href('/en/guide/start/troubleshooting')">Open the table →</a></article>
<article class="ds-card"><span class="tag">FAQ</span><h3>FAQ</h3><p>Conceptual questions such as "why does the third-level cache store a factory".</p><a class="ds-card-link" :href="$href('/en/faq')">Read the FAQ →</a></article>
</div>
