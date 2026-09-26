---
title: 常见报错排查
---

<DsDocHead title="常见报错排查" group="快速开始" :meta="['预计阅读 4 分钟', '8 条常见问题']">

按报错信息找答案。输入关键词即时过滤，命中会高亮 —— 表里的现象、原因、解决一一对应。

</DsDocHead>

<DsErrorTable />

## 还找不到？

<div class="ds-grid ds-grid--2">
<article class="ds-card"><span class="tag">ASK</span><h3>在 GitHub 提问</h3><p>把你的报错信息、JDK 版本与执行的命令一并贴上，能更快定位。</p><a class="ds-card-link" href="https://github.com/wychmod/mini-spring/issues" target="_blank" rel="noreferrer">提交 Issue →</a></article>
<article class="ds-card"><span class="tag">FAQ</span><h3>先看常见问题</h3><p>「为什么三级缓存要存工厂」这类原理困惑，在 FAQ 里集中回答。</p><a class="ds-card-link" :href="$href('/faq')">常见问题 FAQ →</a></article>
</div>

## 排查思路

::: tip 先确认三件事
1. **模块对不对** —— 用 `mvn test -pl mini-spring-iteration` 显式指定模块，避免聚合工程下的歧义。
2. **资源在不在 classpath** —— `spring.xml` 必须位于 `src/main/resources`，否则 `classpath:` 前缀解析不到。
3. **注解处理开没开** —— IDEA 中 `Settings → Build → Compiler → Annotation Processors` 勾选启用。
:::

::: warning 关于循环依赖卡死
如果测试在两个互相依赖的 Bean 上卡住，先检查 `addSingletonFactory` 是否在**属性填充之前**调用。
顺序错了，半成品就不会进第三级缓存，`getBean` 会一直递归等待。
:::
