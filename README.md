<div align="center">

![mini](img/mini-spring1.png)

# mini-spring

**手写spring：从0开始手写一个spring框架**

</div>

<div align="center">

![Static Badge](https://img.shields.io/badge/license-Apache-blue)  ![Static Badge](https://img.shields.io/badge/Java-v1.8-blue) ![GitHub commit activity (branch)](https://img.shields.io/github/commit-activity/t/wychmod/mini-spring?style=social)
![GitHub Repo stars](https://img.shields.io/github/stars/wychmod/mini-spring?style=social) ![Static Badge](https://img.shields.io/badge/QQ-545480453-green)


</div>

## 简介

本项目是一个手写spring框架的项目，从0开始手写一个spring框架，实现spring的基本功能，包括IOC、DI、AOP、MVC等功能。

项目分为两部分：

- mini-spring-original：通过30个类来实现spring的基本功能，功能简洁实用，可以浅显易懂理解spring的DI、IOC、AOP、MVC等功能。参考自[《Spring 5核心原理与30个类手写实战》](https://book.douban.com/subject/34466260/)
- mini-spring-iteration: 符合spring源码风格的spring框架，参照源码，通过分治、抽象和知识(设计模式、设计原则)实现了spring的基本功能，包括IOC、DI、AOP等功能。参考自[《手写Spring：渐进式源码实践》](https://book.douban.com/subject/36139432/)

<br/>
<div align="center">
    <a href="https://book.douban.com/subject/36139432/" target="_blank">
    <img src="https://github.com/wychmod/mini-spring/blob/main/img/book1.png?raw=true" width="250px">
    </a>
    <a href="https://book.douban.com/subject/36139432/" target="_blank">
    <img src="https://github.com/wychmod/mini-spring/blob/main/img/book2.png?raw=true" width="250px">
    </a>
</div>
<br/>

> 代码均有注释，可以参照下面的图片和对应代码进行spring的理解与学习

## Bean的生命周期
![img.png](img/img.png)

## Spring类加载全流程
![img_1.png](img/img_1.png)

## Spring IoC的类图
![img_2.png](img/img_2.png)

## Spring AOP的类图
![img_3.png](img/img_3.png)

## Spring 三级缓存解决循环依赖
![img_4.png](img/img_4.png)