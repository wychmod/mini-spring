---
layout: page
sidebar: false
title: 更新日志
description: mini-spring 的版本变更记录
---

<DsDocHead narrow title="更新日志" :meta="['2 个模块', '4 个版本', 'Apache-2.0']">

按时间倒序记录每个版本的变化。更早的变更请查看 GitHub Releases。

</DsDocHead>

<section class="ds-section ds-section--narrow">
<div class="ds-version"><div class="ds-version-head"><span class="ver">v2.0</span><span class="date">2026-09-26</span><span class="ds-badge ds-badge--accent">最新</span></div>
<ul class="ds-changes">
<li><span class="ds-badge ds-badge--success">新增</span>双语文档站上线，全部章节提供英文镜像</li>
<li><span class="ds-badge ds-badge--success">新增</span>5 张架构图重绘为矢量 SVG，可缩放、可下载</li>
<li><span class="ds-badge ds-badge--success">新增</span>类参考索引：127 个框架类的一句话职责，支持搜索与筛选</li>
<li><span class="ds-badge ds-badge--success">新增</span>两条路径对比、学习路线、常见问题与报错排查等分流页</li>
<li><span class="ds-badge ds-badge--violet">重构</span>文档站信息架构重排为「门户 / 分流 / 文档 / 支撑」四层</li>
<li><span class="ds-badge ds-badge--warning">修复</span>类参考索引中重复收录一个类，总数由 129 校正为 127</li>
</ul>
</div>
<div class="ds-version"><div class="ds-version-head"><span class="ver">v1.2</span><span class="date">2026-08-18</span></div>
<ul class="ds-changes">
<li><span class="ds-badge ds-badge--success">新增</span>事件监听与发布机制：<code>ApplicationEventMulticaster</code> 与生命周期事件</li>
<li><span class="ds-badge ds-badge--success">新增</span><code>ConversionService</code> 类型转换体系</li>
<li><span class="ds-badge ds-badge--success">新增</span><code>BeanFactoryPostProcessor</code> 扩展点</li>
<li><span class="ds-badge ds-badge--warning">修复</span>属性填充阶段对 <code>Aware</code> 回调的顺序问题</li>
</ul>
</div>
<div class="ds-version"><div class="ds-version-head"><span class="ver">v1.1</span><span class="date">2026-07-20</span></div>
<ul class="ds-changes">
<li><span class="ds-badge ds-badge--success">新增</span>Advisor 自动织入：<code>DefaultAdvisorAutoProxyCreator</code></li>
<li><span class="ds-badge ds-badge--success">新增</span><code>@Value</code> / <code>@Qualifier</code> 注解支持</li>
<li><span class="ds-badge ds-badge--success">新增</span>三级缓存完整实现，解决字段注入的循环依赖</li>
</ul>
</div>
<div class="ds-version"><div class="ds-version-head"><span class="ver">v1.0</span><span class="date">2026-06-15</span></div>
<ul class="ds-changes">
<li><span class="ds-badge ds-badge--success">新增</span>首个可用版本：IoC + DI + AOP 主干打通</li>
<li><span class="ds-badge ds-badge--success">新增</span><code>CircleTest</code> 循环依赖演示</li>
<li><span class="ds-badge ds-badge--success">新增</span>极简 MVC 模块 mini-spring-original</li>
</ul>
</div>
<div class="ds-btn-row" style="margin-top: 28px">
<a class="ds-btn ds-btn--ghost ds-btn--sm" href="https://github.com/wychmod/mini-spring/releases" target="_blank" rel="noreferrer">更早的变更请查看 GitHub Releases →</a>
</div>
</section>
