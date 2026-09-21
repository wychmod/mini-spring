---
title: 事件监听与发布
---

# 事件监听与发布

容器不只是一个 Bean 工厂，它还会在关键节点「广播事件」，让外部代码无需侵入容器就能感知生命周期。这一章拆开 `refresh()` 里与事件相关的三步。

## 三个角色

```java
// 发布者：ApplicationContext 本身实现了 ApplicationEventPublisher
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}

// 事件：继承 JDK 的 EventObject
public abstract class ApplicationEvent extends EventObject { ... }

// 监听器：泛型约束只收关心的事件
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
```

发布者与监听器之间隔着**广播器** `ApplicationEventMulticaster`，默认实现 `SimpleApplicationEventMulticaster` 在 `refresh()` 第 6 步被创建并注册为单例。

## refresh 中的三步

```java
// 6. 初始化事件广播器
private void initApplicationEventMulticaster() {
    applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
    beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
}

// 7. 注册监听器：把容器中所有 ApplicationListener 收编进广播器
private void registerListeners() {
    Collection<ApplicationListener> applicationListeners =
            getBeansOfType(ApplicationListener.class).values();
    for (ApplicationListener listener : applicationListeners) {
        applicationEventMulticaster.addApplicationListener(listener);
    }
}

// 9. 发布容器刷新完成事件
private void finishRefresh() {
    publishEvent(new ContextRefreshedEvent(this));
}
```

发布最终落到 `multicastEvent(event)`——遍历监听器，逐个回调 `onApplicationEvent`。这里有一个典型的**解耦设计**：容器只依赖抽象的 `ApplicationEventPublisher` / `ApplicationEventMulticaster`，具体监听器是谁、有几个，容器一概不知。

## 两个生命周期事件

| 事件 | 发布时机 | 典型用途 |
| --- | --- | --- |
| `ContextRefreshedEvent` | `refresh()` 完成后 | 预热缓存、启动后台任务 |
| `ContextClosedEvent` | `close()` 发布事件之后、销毁单例之前 | 优雅停机、资源回收 |

关闭链路值得注意：`registerShutdownHook()` 把 `close()` 挂到 JVM 钩子，`close()` **先发事件、再销毁**——监听器可以在 Bean 还活着的时候做完收尾工作。

## 自定义事件

事件体系是开放的：继承 `ApplicationEvent` 定义自己的事件，实现 `ApplicationListener<MyEvent>` 注册进容器，任何时候 `publishEvent` 即可触达。这套「观察者模式 + 广播器」的结构，与 Spring 真源码完全一致。
