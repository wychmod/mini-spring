---
layout: page
sidebar: false
title: 学习路线
description: 三种档位 —— 速览 1 小时 / 标准 1 周 / 精读 3 周，按你的时间预算选
---

<section class="ds-section ds-section--wide">
  <header class="ds-pagehead" style="padding-top: 0">
    <nav class="ds-breadcrumb" aria-label="面包屑">
      <a href="./">首页</a>
      <span>/</span>
      <span>学习路线</span>
    </nav>
    <h1>学习路线</h1>
    <p class="ds-lede">
      三种档位，按你的时间预算选。每个节点都写清了产出物与验收标准 ——
      不是「读完这章」，而是「读完这章你能做到什么」。
    </p>
  </header>
  <DsRoadmap />
</section>

<section class="ds-section ds-section--wide ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">At a Glance</span>
      <h2 class="ds-heading">三档路线对照</h2>
      <p class="ds-sub">同一套内容，三种读法。选错了也不要紧，随时可以往下加档。</p>
    </div>
    <div class="ds-grid ds-grid--3">
      <article class="ds-card" style="--ds-card-accent: #27c9ff; --ds-card-accent-soft: rgba(39,201,255,.12)">
        <span class="tag">速览版</span>
        <h3>1 小时</h3>
        <p>读图 + 读结论，跳过所有源码细节。适合面试前把概念重新捋一遍。</p>
        <ul style="margin: 12px 0 0; padding-left: 18px; font-size: 13.5px; color: var(--ds-text-2)">
          <li>5 张架构图</li>
          <li>refresh() 九步概览</li>
          <li>三级缓存的一句话解释</li>
        </ul>
      </article>
      <article class="ds-card" style="--ds-card-accent: #2e63e7; --ds-card-accent-soft: rgba(46,99,231,.12)">
        <span class="tag">标准版</span>
        <h3>1 周</h3>
        <p>读全部文档 + 跑通测试，跟着主线把容器从启动到销毁走一遍。</p>
        <ul style="margin: 12px 0 0; padding-left: 18px; font-size: 13.5px; color: var(--ds-text-2)">
          <li>容器启动九步调用链</li>
          <li>Bean 生命周期三条填充路径</li>
          <li>三级缓存的降级逻辑</li>
        </ul>
      </article>
      <article class="ds-card" style="--ds-card-accent: #7c5ce8; --ds-card-accent-soft: rgba(124,92,232,.12)">
        <span class="tag">精读版</span>
        <h3>3 周</h3>
        <p>逐行对照真实 Spring 源码，找出 mini 版省略了什么、为什么可以省。</p>
        <ul style="margin: 12px 0 0; padding-left: 18px; font-size: 13.5px; color: var(--ds-text-2)">
          <li>refresh() 每一行对应的类</li>
          <li>手写一遍缓存降级</li>
          <li>自定义 Advisor 与事件</li>
        </ul>
      </article>
    </div>
    <div class="ds-btn-row" style="margin-top: 32px">
      <a class="ds-btn ds-btn--primary" href="./guide/preface">从第一条路线开始 →</a>
      <a class="ds-btn ds-btn--ghost" href="./compare">先看两条路径对比 →</a>
    </div>
  </div>
</section>
