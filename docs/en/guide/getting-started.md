---
title: Setup & Run
---

# Setup & Run

## Requirements

- JDK 1.8
- Maven 3.x
- Any IDE you like (IDEA / Eclipse both fine)

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

## Run your first test case

The iteration module's test directory contains a complete example: `spring.xml` defines beans and their dependencies, and `ApiTest#test_convert` pulls a bean out of the container with type conversion applied.

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

Behind these three lines the container has already: located the config → loaded resources → parsed and registered definitions → run the refresh bootstrap → instantiated → populated properties → invoked Aware callbacks → initialized → published events. That is exactly what the next chapters unpack.

## The one-level-cache demo

`CircleTest` is a plain `main` method with no framework involved — about 20 lines showing the essence of circular dependencies: put the half-built instance into a cache right after instantiation.

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
