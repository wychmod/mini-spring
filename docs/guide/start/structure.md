---
title: 目录结构导览
---

<DsDocHead title="目录结构导览" group="快速开始" :meta="['预计阅读 5 分钟', '10 个包分组']">

每个包负责什么，一页说完。切换模块即可看到两个实现各自的包结构 —— 数据与[类参考索引](/reference)同源。

</DsDocHead>

## 两个模块的根路径

- **mini-spring-iteration** 的框架代码位于 `com/wychmod/springframework/` 下，按 Spring 源码的分层方式组织。
- **mini-spring-original** 的框架代码位于 `com/wychmod/spring/framework/` 下，按功能平铺组织，链路更短。

<DsStructure />

## 命名与分层的三条约定

::: tip 约定 1 · 接口在父包，实现在 support 子包
`BeanFactory` 定义在 `beans/`，而具体实现 `DefaultListableBeanFactory` 落在 `beans/factory/support/`。
父包只放契约，子包放实现，读代码时先看父包接口就能知道这个模块要解决什么问题。
:::

::: tip 约定 2 · 前缀体现抽象层级
`Abstract*` 是模板骨架（如 `AbstractBeanFactory` 固定了 `getBean` 的流程），`Default*` 是可直接使用的默认实现。
遇到 `Abstract` 先找 `doXxx` 钩子方法，那是留给子类改写的缝。
:::

::: tip 约定 3 · 注解与注解处理器分离
`@Autowired` 这类注解定义在 `beans/factory/annotation/`，而真正处理它的是 `AutowiredAnnotationBeanPostProcessor`。
注解只是标记，处理逻辑统一收敛到后置处理器，这是 Spring 扩展点的标准做法。
:::

## 下一步

<div class="ds-grid ds-grid--3">
<article class="ds-card"><span class="tag">RUN</span><h3>跑起来</h3><p>克隆仓库并运行第一个测试用例。</p><a class="ds-card-link" :href="$href('/guide/getting-started')">环境与运行 →</a></article>
<article class="ds-card"><span class="tag">MAP</span><h3>看类图</h3><p>按功能模块铺开的 IoC 全景类图。</p><a class="ds-card-link" :href="$href('/guide/ioc/class-diagram')">IoC 全景类图 →</a></article>
<article class="ds-card"><span class="tag">INDEX</span><h3>查索引</h3><p>127 个类的一句话职责，可即时筛选。</p><a class="ds-card-link" :href="$href('/reference')">类参考索引 →</a></article>
</div>
