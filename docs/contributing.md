---
layout: page
sidebar: false
title: 贡献指南
description: 无论改代码、补文档还是提 issue，都欢迎
---

<DsDocHead title="贡献指南" :meta="['5 步流程', '代码规范', '文档约定']">

无论改代码、补文档还是提 issue，都欢迎。这一页说明这个项目的约定，让你第一次提交就能过。

</DsDocHead>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Workflow</span><h2 class="ds-heading">贡献流程</h2><p class="ds-sub">五步走完，全程不超过十分钟。</p></div>
<div class="ds-steps ds-steps--5">
<div class="ds-step"><span class="num">01</span><h4>Fork</h4><p>把仓库 Fork 到你自己的 GitHub 账号下。</p></div>
<div class="ds-step"><span class="num">02</span><h4>建分支</h4><p>按类型命名：<code>feat/xxx</code> 或 <code>fix/xxx</code>。</p></div>
<div class="ds-step"><span class="num">03</span><h4>改改改</h4><p>保持原有风格，代码与文档都算贡献。</p></div>
<div class="ds-step"><span class="num">04</span><h4>自测</h4><p>本地 <code>mvn test</code> 必须全部通过。</p></div>
<div class="ds-step"><span class="num">05</span><h4>提 PR</h4><p>说明改了什么，并关联相关 issue。</p></div>
</div>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Conventions</span><h2 class="ds-heading">代码规范摘要</h2></div>
<table class="ds-compare-table"><thead><tr><th style="width: 22%">项</th><th style="width: 40%">约定</th><th style="width: 38%">示例</th></tr></thead><tbody><tr><td data-label="项">命名</td><td data-label="约定">与 Spring 源码保持一致，不另造词</td><td data-label="示例"><code>DefaultSingletonBeanRegistry</code></td></tr><tr><td data-label="项">注释</td><td data-label="约定">每个类与方法都要有中文注释，说明「为什么」</td><td data-label="示例"><code>/** 一级缓存，普通对象 */</code></td></tr><tr><td data-label="项">包结构</td><td data-label="约定">按功能分层，不跨层反向引用</td><td data-label="示例"><code>beans</code> / <code>context</code> / <code>aop</code></td></tr><tr><td data-label="项">提交信息</td><td data-label="约定">类型(范围): 描述</td><td data-label="示例"><code>feat(aop): 支持切点表达式</code></td></tr><tr><td data-label="项">文档改动</td><td data-label="约定">中文与英文版本同步修改</td><td data-label="示例"><code>docs/</code> + <code>docs/en/</code></td></tr></tbody></table>
</div>
</section>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Glossary</span><h2 class="ds-heading">文档写作约定</h2><p class="ds-sub">术语统一译法，避免同一概念出现多种写法。</p></div>
<table class="ds-compare-table"><thead><tr><th style="width: 30%">术语</th><th style="width: 70%">统一用法</th></tr></thead><tbody><tr><td data-label="术语"><code>BeanDefinition</code></td><td data-label="用法">不翻译，保留英文</td></tr><tr><td data-label="术语">提前暴露</td><td data-label="用法">统一用「提前暴露」，不用「提前引用」</td></tr><tr><td data-label="术语">三级缓存</td><td data-label="用法">统一用「三级缓存」，三个缓存分别用「一级 / 二级 / 三级」指代</td></tr><tr><td data-label="术语"><code>refresh()</code></td><td data-label="用法">带括号、等宽字体，九步流程称「刷新九步」</td></tr><tr><td data-label="术语">切面 / 切点 / 通知</td><td data-label="用法">分别对应 Aspect / Pointcut / Advice</td></tr></tbody></table>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Checklist</span><h2 class="ds-heading">PR 检查清单</h2><p class="ds-sub">提交前逐条自查，能把一次通过率提高很多。</p></div>
<ul class="ds-checklist"><li><span class="box"></span>代码可编译，<code>mvn test</code> 全部通过</li><li><span class="box"></span>新增类已补充中文注释</li><li><span class="box"></span>文档改动已同步英文版</li><li><span class="box"></span>提交信息符合「类型(范围): 描述」格式</li><li><span class="box"></span>已关联相关 issue</li></ul>
<div class="ds-btn-row" style="margin-top: 28px">
<a class="ds-btn ds-btn--primary" href="https://github.com/wychmod/mini-spring/issues" target="_blank" rel="noreferrer">查看 open issues →</a>
<a class="ds-btn ds-btn--ghost" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">阅读源码 →</a>
</div>
</div>
</section>
