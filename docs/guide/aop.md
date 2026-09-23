---
title: AOP 原理与实现
---

# AOP 原理与实现

AOP 要回答三个问题：**在哪切**（切点）、**切什么**（通知）、**怎么切**（代理）。mini-spring 的 `aop` 包按这个结构组织。

![Spring AOP 类图](/images/aop-class-diagram.png)

## 三个问题的实现

**在哪切 —— 切点表达式**

`Pointcut` 由 `ClassFilter`（类匹配）与 `MethodMatcher`（方法匹配）组成，`AspectJExpressionPointcut` 同时实现三者，用 `execution(* com..*.*(..))` 这类 AspectJ 表达式完成匹配。

**切什么 —— 通知与适配器**

```java
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice advice;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.advice.before(methodInvocation.getMethod(),
                methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
```

通知接口 `MethodBeforeAdvice` 来自用户视角，`MethodInterceptor` / `MethodInvocation` 来自 aopalliance 拦截器链视角，`MethodBeforeAdviceInterceptor` 就是两者的适配器：**先 before，再放行**。链上真正的调用由 `ReflectiveMethodInvocation#proceed → method.invoke(target, args)` 完成。

**怎么切 —— 双代理实现**

```java
private AopProxy createAopProxy() {
    if (advisedSupport.isProxyTargetClass()) {
        return new Cglib2AopProxy(advisedSupport);
    }
    return new JdkDynamicAopProxy(advisedSupport);
}
```

- `JdkDynamicAopProxy`：实现 `InvocationHandler`，`invoke` 时先用 `MethodMatcher` 判断，命中才走拦截器链，否则直接反射调用原方法
- `Cglib2AopProxy`：`Enhancer` 子类代理 + `DynamicAdvisedInterceptor` 拦截

代理的选择参数由 `AdvisedSupport` 汇总：`targetSource`（目标对象）、`methodInterceptor`（通知）、`methodMatcher`（匹配器）、`proxyTargetClass`（代理方式）。

## 融入 Bean 生命周期：自动代理

手动给每个 Bean 配代理太笨。`DefaultAdvisorAutoProxyCreator` 实现了 `InstantiationAwareBeanPostProcessor`，把织入挂进 [Bean 生命周期](/guide/ioc/lifecycle)：

```java
@Override
public Object postProcessAfterInitialization(Object bean, String beanName) {
    if (!earlyProxyReferences.contains(beanName)) {
        return wrapIfNecessary(bean, beanName);
    }
    return bean;
}

protected Object wrapIfNecessary(Object bean, String beanName) {
    if (isInfrastructureClass(bean.getClass())) return bean;
    Collection<AspectJExpressionPointcutAdvisor> advisors =
            beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
    for (AspectJExpressionPointcutAdvisor advisor : advisors) {
        if (!advisor.getPointcut().getClassFilter().matches(bean.getClass())) continue;
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(bean));
        advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
        advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
        advisedSupport.setProxyTargetClass(true);
        return new ProxyFactory(advisedSupport).getProxy();
    }
    return bean;
}
```

生成代理的时机有两个，配合 [三级缓存](/guide/ioc/circular-deps) 使用：

1. **初始化后**（常态）：`postProcessAfterInitialization → wrapIfNecessary`
2. **发生循环依赖时**（提前）：三级缓存的工厂被调用 → `getEarlyBeanReference → wrapIfNecessary`，并用 `earlyProxyReferences` 记录已代理的 beanName，避免初始化后**重复代理**

## 配一个切面试试

::: info 写法示意
仓库目前没有附带可运行的 AOP 示例，下面是按照框架能力的推荐写法：把切面顾问与自动代理创建器都注册为普通 Bean 即可。
:::

```xml
<bean class="com.wychmod.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor">
    <property name="expression" value="execution(* com.wychmod.springframework.test.bean.*.*(..))"/>
    <property name="advice" ref="beforeAdvice"/>
</bean>

<!-- 自动代理创建器：扫描 Advisor 并在 Bean 创建时织入 -->
<bean class="com.wychmod.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>
```

容器启动后，所有匹配该表达式的 Bean 在创建时自动变成代理对象——用户代码零感知。
