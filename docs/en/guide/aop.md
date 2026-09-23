---
title: AOP
---

# AOP

AOP answers three questions: **where to cut** (pointcut), **what to apply** (advice), and **how to apply it** (proxy). The `aop` package is organized along exactly those lines.

![AOP class diagram](/images/aop-class-diagram.png)

## The three questions

**Where to cut — pointcut expressions**

A `Pointcut` is composed of a `ClassFilter` (class matching) and a `MethodMatcher` (method matching). `AspectJExpressionPointcut` implements all three, matching with AspectJ expressions like `execution(* com..*.*(..))`.

**What to apply — advice and the adapter**

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

The advice interface `MethodBeforeAdvice` speaks the user's language; `MethodInterceptor` / `MethodInvocation` speak the aopalliance interception-chain language. `MethodBeforeAdviceInterceptor` is the adapter between them: **first before, then proceed**. The actual invocation is completed by `ReflectiveMethodInvocation#proceed → method.invoke(target, args)`.

**How to apply — dual proxy implementations**

```java
private AopProxy createAopProxy() {
    if (advisedSupport.isProxyTargetClass()) {
        return new Cglib2AopProxy(advisedSupport);
    }
    return new JdkDynamicAopProxy(advisedSupport);
}
```

- `JdkDynamicAopProxy`: implements `InvocationHandler`; on `invoke` it first checks the `MethodMatcher` — only matching calls go through the interception chain, everything else reflects straight to the target
- `Cglib2AopProxy`: an `Enhancer` subclass proxy with a `DynamicAdvisedInterceptor`

Proxy selection parameters are aggregated in `AdvisedSupport`: `targetSource` (target object), `methodInterceptor` (advice), `methodMatcher` (matching), `proxyTargetClass` (proxy style).

## Fusing into the Bean lifecycle: auto proxying

Configuring a proxy for every bean by hand is clumsy. `DefaultAdvisorAutoProxyCreator` implements `InstantiationAwareBeanPostProcessor`, hooking weaving into the [Bean Lifecycle](/en/guide/ioc/lifecycle):

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

There are two moments where a proxy is created, coordinated with the [three-level cache](/en/guide/ioc/circular-deps):

1. **After initialization** (the normal path): `postProcessAfterInitialization → wrapIfNecessary`
2. **When a circular dependency actually happens** (early): the level-3 factory is invoked → `getEarlyBeanReference → wrapIfNecessary`, with `earlyProxyReferences` recording the proxied beanName to prevent **double proxying** after initialization

## Try configuring an advice

::: info Illustrative example
The repository currently ships no runnable AOP example. The snippet below is the recommended wiring based on the framework's capabilities: register both the advisor and the auto proxy creator as plain beans.
:::

```xml
<bean class="com.wychmod.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor">
    <property name="expression" value="execution(* com.wychmod.springframework.test.bean.*.*(..))"/>
    <property name="advice" ref="beforeAdvice"/>
</bean>

<!-- The auto proxy creator: scans Advisors and weaves at bean creation time -->
<bean class="com.wychmod.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>
```

After the container starts, every bean matching the expression automatically becomes a proxy at creation time — with zero awareness in user code.
