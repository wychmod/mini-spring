---
title: Events
---

# Events

The container is more than a bean factory — it broadcasts events at key moments, letting external code observe the lifecycle without intruding into the container. This chapter unpacks the three event-related steps inside `refresh()`.

## Three roles

```java
// The publisher: ApplicationContext itself implements ApplicationEventPublisher
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}

// The event: extends the JDK's EventObject
public abstract class ApplicationEvent extends EventObject { ... }

// The listener: generics constrain it to the events it cares about
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
```

Between the publisher and the listeners sits the **multicaster** `ApplicationEventMulticaster`; the default implementation `SimpleApplicationEventMulticaster` is created and registered as a singleton in step 6 of `refresh()`.

## The three steps in refresh

```java
// 6. initialize the event multicaster
private void initApplicationEventMulticaster() {
    applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
    beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
}

// 7. register listeners: collect every ApplicationListener in the container
private void registerListeners() {
    Collection<ApplicationListener> applicationListeners =
            getBeansOfType(ApplicationListener.class).values();
    for (ApplicationListener listener : applicationListeners) {
        applicationEventMulticaster.addApplicationListener(listener);
    }
}

// 9. publish the refresh-complete event
private void finishRefresh() {
    publishEvent(new ContextRefreshedEvent(this));
}
```

Publishing bottoms out at `multicastEvent(event)` — iterate the listeners and call `onApplicationEvent` on each. Note the classic **decoupling**: the container only depends on the abstract `ApplicationEventPublisher` / `ApplicationEventMulticaster`; it knows nothing about which listeners exist.

## Two lifecycle events

| Event | Published when | Typical use |
| --- | --- | --- |
| `ContextRefreshedEvent` | After `refresh()` completes | Warm up caches, start background tasks |
| `ContextClosedEvent` | In `close()`, after publishing, before destroying singletons | Graceful shutdown, resource cleanup |

The shutdown chain is worth noticing: `registerShutdownHook()` attaches `close()` to a JVM hook, and `close()` **publishes the event first, destroys singletons second** — listeners finish their cleanup while the beans are still alive.

## Custom events

The event system is open: extend `ApplicationEvent` to define your own, implement `ApplicationListener<MyEvent>` to register a listener, and call `publishEvent` whenever you like. This "observer pattern + multicaster" structure is exactly what real Spring uses.
