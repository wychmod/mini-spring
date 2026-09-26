---
layout: page
sidebar: false
title: 关于 mini-spring
description: 一个为了真正读懂 Spring 而写起的项目
---

<DsDocHead title="关于 mini-spring" :meta="['Apache-2.0', '2 个模块', '127 个框架类']">

一个为了「真正读懂 Spring」而写起的项目 —— 不是再写一遍教程，而是把源码拆开、重新组装一遍。

</DsDocHead>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Story</span><h2 class="ds-heading">项目缘起</h2></div>
<div style="max-width: 680px; font-size: 15px; line-height: 1.85; color: var(--ds-text-2)">
<p style="margin: 0 0 20px">写了几年 Spring，<code>@Autowired</code> 闭着眼都能敲，可一旦被问到「循环依赖为什么是三级缓存而不是两级」，就只剩下背答案。真正让我下决心的是读源码时的挫败感：<code>refresh()</code> 里 12 个方法层层嵌套，一眼望不到底。于是我想，与其硬啃 15 万行源码，不如亲手写一个几百行的迷你版 —— 只有自己动手把 <code>BeanDefinition</code> 注册、实例化、属性填充、初始化串起来，才算真的懂。</p>
<p style="margin: 0">写着写着发现，「迷你版」并不只有一种写法。有人需要一条最短的路径快速建立直觉，有人希望在命名和分层上和 Spring 严格对齐、逐段比对。所以最终拆成了两个模块：<b>mini-spring-original</b> 用 29 个类讲清主干，<b>mini-spring-iteration</b> 用 98 个类把每个扩展点都补上。两条路径共享同一套设计思想，你可以按自己的经验选择起点。</p>
</div>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Books</span><h2 class="ds-heading">参考书目</h2><p class="ds-sub">两个模块各自对应一本配套书籍，代码与书中的章节一一对应。</p></div>
<div class="ds-books">
<a class="ds-book" href="https://book.douban.com/subject/35006215/" target="_blank" rel="noreferrer"><img :src="$href('/images/book1.png')" alt="Spring 5 核心原理与 30 个类手写实战 封面"><span class="info"><span class="name">《Spring 5 核心原理与 30 个类手写实战》</span><span class="note">对应 mini-spring-original · 极简版</span></span></a>
<a class="ds-book" href="https://book.douban.com/subject/36331424/" target="_blank" rel="noreferrer"><img :src="$href('/images/book2.png')" alt="手写 Spring：渐进式源码实践 封面"><span class="info"><span class="name">《手写 Spring：渐进式源码实践》</span><span class="note">对应 mini-spring-iteration · 渐近式</span></span></a>
</div>
</div>
</section>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Stack</span><h2 class="ds-heading">技术选型</h2></div>
<div class="ds-grid ds-grid--3">
<article class="ds-card"><span class="ds-icon-box">◈</span><h3>VitePress</h3><p>文档站基于 VitePress 构建。选它是因为 Vue 组件可以无缝嵌入 markdown —— 类参考索引、图集、学习路线都做成了可交互组件，而不是静态截图。</p></article>
<article class="ds-card"><span class="ds-icon-box">◆</span><h3>Java 8</h3><p>框架代码锁定 Java 8。真实 Spring 5 的最低要求也是 8，保持同样的语言基线，才能让「这段代码为什么这么写」的讨论落在同一个语境里。</p></article>
<article class="ds-card"><span class="ds-icon-box">◇</span><h3>双语同构</h3><p>中文与英文两套路由共享同一批组件，数据从同一份 <code>classes.ts</code> 读取。翻译不会漂移，改一处两边同时生效。</p></article>
</div>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Thanks</span><h2 class="ds-heading">致谢</h2><p class="ds-sub">感谢每一位提交过 issue、PR 或在群里指出错误的朋友。</p></div>
<ul class="ds-avatars"><li title="wychmod">wy</li><li title="contributor">+1</li><li title="contributor">+2</li><li title="contributor">+3</li><li title="contributor">+4</li><li title="contributor">+5</li><li title="contributor">+6</li><li title="contributor">+7</li></ul>
</div>
</section>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Contact</span><h2 class="ds-heading">联系方式</h2></div>
<div class="ds-grid ds-grid--3">
<div class="ds-mini-card"><span class="k">GitHub</span><span class="v mono">@wychmod</span></div>
<div class="ds-mini-card"><span class="k">Issues</span><span class="v mono">问题与建议</span></div>
<div class="ds-mini-card"><span class="k">License</span><span class="v mono">Apache-2.0</span></div>
</div>
<div class="ds-btn-row" style="margin-top: 28px">
<a class="ds-btn ds-btn--primary" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">查看源码</a>
<a class="ds-btn ds-btn--ghost" :href="$href('/contributing')">贡献指南 →</a>
</div>
</section>
