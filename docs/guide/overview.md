---
layout: page
sidebar: false
title: 项目总览：两条实现路径
---

<DsDocHead title="项目总览：两条实现路径" :meta="['预计阅读 6 分钟', '2 个模块', '127 个框架类']">

同一套 Spring 思想，两种完全不同的节奏。这一页从**代码结构**的角度看两条路径 —— 想直接决定学哪个，走[两条路径对比](/compare)。

</DsDocHead>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">At a Glance</span><h2 class="ds-heading">模块对照</h2><p class="ds-sub">先建立直觉，再深入细节 —— 也可以按经验直接选。</p></div>
<table class="ds-compare-table"><thead><tr><th style="width: 24%">维度</th><th style="width: 38%">mini-spring-original</th><th style="width: 38%">mini-spring-iteration</th></tr></thead><tbody><tr><td data-label="维度">定位</td><td data-label="original">29 个类的极简版</td><td data-label="iteration">98 个类的渐进式实现</td></tr><tr><td data-label="维度">类命名</td><td data-label="original">独立命名，链路极短</td><td data-label="iteration">与 Spring 源码同名同分层</td></tr><tr><td data-label="维度">入口</td><td data-label="original">注解扫描 + DispatchServlet</td><td data-label="iteration">ClassPathXmlApplicationContext + refresh()</td></tr><tr><td data-label="维度">配置方式</td><td data-label="original">注解扫描</td><td data-label="iteration">XML + 注解扫描</td></tr><tr><td data-label="维度">AOP</td><td data-label="original">双代理 + 方法拦截链</td><td data-label="iteration">双代理 + Advisor 自动织入</td></tr><tr><td data-label="维度">循环依赖</td><td data-label="original">一级缓存演示</td><td data-label="iteration">完整三级缓存</td></tr><tr><td data-label="维度">适合谁</td><td data-label="original">第一次接触，建立直觉</td><td data-label="iteration">读过一遍后，深入细节</td></tr></tbody></table>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Packages</span><h2 class="ds-heading">包结构：一平一深</h2><p class="ds-sub">original 平铺在 4 个功能包里；iteration 按 Spring 的分层方式展开成 6 个包。</p></div>
<div class="ds-grid ds-grid--2"><pre class="ds-tree"><span class="root">mini-spring-original</span>
framework/
<span class="conn"> ├─</span> annotation/   5 个类
<span class="conn"> ├─</span> aop/         14 个类
<span class="conn"> ├─</span> beans/        3 个类
<span class="conn"> └─</span> webmvc/       7 个类
</pre><pre class="ds-tree"><span class="root">mini-spring-iteration</span>
springframework/
<span class="conn"> ├─</span> beans/       41 个类
<span class="conn"> ├─</span> context/     21 个类
<span class="conn"> ├─</span> aop/         18 个类
<span class="conn"> ├─</span> core/        14 个类
<span class="conn"> ├─</span> stereotype/   1 个类
<span class="conn"> └─</span> utils/        3 个类
</pre></div>
<div class="ds-btn-row" style="margin-top: 24px"><a class="ds-btn ds-btn--ghost ds-btn--sm" :href="$href('/guide/start/structure')">目录结构导览 →</a><a class="ds-btn ds-btn--ghost ds-btn--sm" :href="$href('/reference')">类参考索引 →</a></div>
</div>
</section>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Coverage</span><h2 class="ds-heading">功能覆盖矩阵</h2><p class="ds-sub">iteration 覆盖了主干能力；original 额外提供了极简 MVC，但省略了缓存与扩展点。</p></div>
<table class="ds-compare-table"><thead><tr><th style="width: 40%">能力</th><th style="width: 30%">mini-spring-original</th><th style="width: 30%">mini-spring-iteration</th></tr></thead><tbody><tr><td data-label="能力">IoC 容器</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="能力">依赖注入</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="能力">AOP 切面</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="能力">注解扫描</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="能力">MVC 支持</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--plain">—</span></td></tr><tr><td data-label="能力">三级缓存</td><td data-label="original"><span class="ds-badge ds-badge--plain">—</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="能力">事件机制</td><td data-label="original"><span class="ds-badge ds-badge--plain">—</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="能力">类型转换</td><td data-label="original"><span class="ds-badge ds-badge--plain">—</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="能力">容器扩展点</td><td data-label="original"><span class="ds-badge ds-badge--plain">—</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr></tbody></table>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Next</span><h2 class="ds-heading">接下来去哪</h2></div>
<div class="ds-grid ds-grid--3"><article class="ds-card"><span class="tag">RUN</span><h3>想先跑起来</h3><p>环境要求、目录结构与第一个测试用例。</p><a class="ds-card-link" :href="$href('/guide/getting-started')">看环境与运行 →</a></article><article class="ds-card"><span class="tag">CHOOSE</span><h3>还在纠结选哪个</h3><p>16 个维度的完整对比与决策树。</p><a class="ds-card-link" :href="$href('/compare')">看完整对比 →</a></article><article class="ds-card"><span class="tag">PLAN</span><h3>想按计划学</h3><p>速览 1 小时 / 标准 1 周 / 精读 3 周。</p><a class="ds-card-link" :href="$href('/roadmap')">看学习路线 →</a></article></div>
</div>
</section>
