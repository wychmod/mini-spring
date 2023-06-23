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
4. 初始化I0C容器，并且实例化对象 -> BeanWrapper
5. 完成DI注入
