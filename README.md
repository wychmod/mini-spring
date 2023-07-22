<div align="center">

![mini](img/mini-spring.png)

# mini-spring

**手写spring：从0开始手写一个spring框架**

</div>

<div align="center">

![Static Badge](https://img.shields.io/badge/license-Apache-blue)  ![Static Badge](https://img.shields.io/badge/Java-v1.8-blue) ![GitHub commit activity (branch)](https://img.shields.io/github/commit-activity/t/wychmod/mini-spring)

</div>

## 实现IoC和DI的功能

###  基本思路
> Ioc和DI的功能在core和beans模块。

1. ApplicationContext 简单地理解为它就是工厂类, getBean()从Ioc容器中去获取一个实例的方法
2. 在调用Servlet init()方法时，就要初始化ApplicationContext
3. 默认是单例，而且是延时加载（lazy)的，DI是在初始化以后发生的
4. Spring中发生DI由getBean()触发
   1. 调用getBean()创建对象
   2. 立即就会发生DI


### Servlet初始化基本流程
1. 调用Servlet init()方法
   - 创建ApplicationContext
2. 读取配置文件(properties、xml、yml) -> BeanDefinitionReader(配置文件保存到了内存中)
3. 扫描相关的类，扫描到的类也变成了BeanDefinition
   - ApplicationContext.getBean()
4. 初始化IOC容器，并且实例化对象 -> BeanWrapper
5. 完成DI注入

### Spring IOC的基本流程

1. 读取配置文件。
2. 解析配置文件，并封装成BeanDefinition。
3. 把BeanDefinition对应的实例放入到容器进行缓存。

### Spring DI的基本流程
1. 循环读取BeanDefinition的缓存信息。
2. 调用getBean()方法创建对象实例。
3. 将创建好的对象实例包装为BeanWrapper对象。
4. 将BeanWrapper对象缓存到IOC容器。
5. 循环IOC容器执行以来注入。

### DI注入时循环依赖如何解决：
A { B b } （A 初始化的时候B还不存在，不能依赖注入）
B { A a } （B 初始化的时候A已经存在，可以依赖注入，再循环一次，把A的B给注入。）
用两个缓存，循环两次
1. 把第一次读取结果为空的BeanDefinition存到第一个缓存中
2. 等第一次循环之后，第二次循环再检查第一次的缓存，再进行赋值