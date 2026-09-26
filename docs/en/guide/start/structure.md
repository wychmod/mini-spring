---
title: Project Structure
---

<DsDocHead title="Project Structure" group="Getting Started" :meta="['5 min read', '10 package groups']">

What each package is responsible for, in one page. Switch modules to see how the two implementations are laid out — the data comes from the same source as the [class index](/en/reference).

</DsDocHead>

## Root paths of the two modules

- **mini-spring-iteration** lives under `com/wychmod/springframework/`, organised the way Spring's own source is layered.
- **mini-spring-original** lives under `com/wychmod/spring/framework/`, organised flat by feature, with much shorter chains.

<DsStructure />

## Three conventions of naming and layering

::: tip Convention 1 · interfaces in the parent package, implementations under support
`BeanFactory` is defined in `beans/`, while `DefaultListableBeanFactory` lives in `beans/factory/support/`.
Parent packages hold contracts only; child packages hold implementations. Reading the parent interface first tells you what the module is meant to solve.
:::

::: tip Convention 2 · prefixes reflect abstraction level
`Abstract*` is a template skeleton (e.g. `AbstractBeanFactory` fixes the `getBean` flow); `Default*` is a ready-to-use default implementation.
When you hit an `Abstract` class, look for `doXxx` hook methods — those are the seams left for subclasses.
:::

::: tip Convention 3 · annotations and their processors are separated
Stereotype annotations such as `@Autowired` are defined in `beans/factory/annotation/`, while the class that actually handles them is `AutowiredAnnotationBeanPostProcessor`.
Annotations are just markers; handling logic is centralised in post-processors — the standard Spring extension pattern.
:::

## Next steps

<div class="ds-grid ds-grid--3">
<article class="ds-card"><span class="tag">RUN</span><h3>Get it running</h3><p>Clone the repo and run your first test.</p><a class="ds-card-link" :href="$href('/en/guide/getting-started')">Setup &amp; Run →</a></article>
<article class="ds-card"><span class="tag">MAP</span><h3>See the map</h3><p>The IoC class diagram laid out by functional area.</p><a class="ds-card-link" :href="$href('/en/guide/ioc/class-diagram')">IoC Class Diagram →</a></article>
<article class="ds-card"><span class="tag">INDEX</span><h3>Browse the index</h3><p>One-line responsibilities for all 127 classes.</p><a class="ds-card-link" :href="$href('/en/reference')">Class Index →</a></article>
</div>
