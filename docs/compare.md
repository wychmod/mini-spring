---
layout: page
sidebar: false
title: 两条实现路径，怎么选
description: 16 个维度逐一对比 mini-spring-original 与 mini-spring-iteration，30 秒给出结论
---

<section class="ds-section ds-section--wide">
  <header class="ds-pagehead" style="padding-top: 0">
    <nav class="ds-breadcrumb" aria-label="面包屑">
      <a href="./">首页</a>
      <span>/</span>
      <span>两条路径对比</span>
    </nav>
    <h1>两条实现路径，怎么选</h1>
    <p class="ds-lede">
      mini-spring 用两个模块讲同一套 Spring 思想，但节奏、命名、覆盖面完全不同。
      这一页把差异摊开成一张 16 维的对照表，读完就能决定从哪条路径开始。
    </p>
  </header>
  <div class="ds-callout" style="margin-bottom: 40px">
    <span class="label">30 秒结论</span>
    <p>推荐先走 <b>mini-spring-original</b> 建立直觉（约 1 周），再切到 <b>mini-spring-iteration</b> 抠细节。</p>
    <p>如果你已有 Spring 使用经验，可以直接从 iteration 开始，跳过 original 也不影响理解。</p>
  </div>
  <h2 class="ds-heading" style="margin-bottom: 16px">维度对照表</h2>
  <DsCompare />
</section>

<section class="ds-section ds-section--wide">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Decision</span>
    <h2 class="ds-heading">两个问题，定位你的起点</h2>
    <p class="ds-sub">不用读完 16 行表格，回答下面两个问题就够了。</p>
  </div>
  <div class="ds-grid ds-grid--2">
    <article class="ds-card" style="--ds-card-accent: #2e63e7; --ds-card-accent-soft: rgba(46,99,231,.12)">
      <span class="tag">Q1</span>
      <h3>想快速建立「Spring 是什么」的直觉吗？</h3>
      <p>
        第一次接触、面试前突击、只想把主线跑通 —— 选 original。
        它用 29 个类把 IoC、DI、AOP 串成最短的一条链路，读起来没有负担。
      </p>
      <a class="ds-card-link" href="./guide/getting-started">从 original 开始 →</a>
    </article>
    <article class="ds-card" style="--ds-card-accent: #7c5ce8; --ds-card-accent-soft: rgba(124,92,232,.12)">
      <span class="tag">Q2</span>
      <h3>已经有了 Spring 的使用经验吗？</h3>
      <p>
        用过 <code>@Autowired</code>、写过切面、被三级缓存问过 —— 直接选 iteration。
        它与 Spring 源码同名同分层，98 个类逐段对照，能把你「知道怎么用」升级成「知道为什么」。
      </p>
      <a class="ds-card-link" href="./guide/preface">从 iteration 开始 →</a>
    </article>
  </div>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">In Detail</span>
      <h2 class="ds-heading">两条路径，各自独有的是什么</h2>
      <p class="ds-sub">除了都会讲的 IoC 与 AOP，两条路径各自还有一些只属于它的章节。</p>
    </div>
    <div class="ds-grid ds-grid--2">
      <div class="ds-path">
        <div class="ds-path-head">
          <span class="win">
            <i style="background: #e2637a"></i>
            <i style="background: #e8a23d"></i>
            <i style="background: #27a882"></i>
          </span>
          mini-spring-original
        </div>
        <div class="ds-path-body">
          <p>
            注解扫描驱动的极简容器，类名与 Spring 源码不同，链路极短，
            配套《Spring 5 核心原理与 30 个类手写实战》，目标是让你在一周内建立整体直觉。
          </p>
          <ul>
            <li>注解扫描容器：<code>@Component</code> 直达 <code>getBean</code></li>
            <li>极简 MVC：<code>DispatchServlet</code> + <code>HandlerMapping</code></li>
            <li>方法拦截链式的 AOP：双代理 + 前置通知</li>
            <li>一级缓存演示循环依赖：<code>CircleTest</code></li>
          </ul>
          <a class="ds-btn ds-btn--ghost ds-btn--sm" href="./guide/getting-started">环境与运行 →</a>
        </div>
      </div>
      <div class="ds-path">
        <div class="ds-path-head">
          <span class="win">
            <i style="background: #2e63e7"></i>
            <i style="background: #27c9ff"></i>
            <i style="background: #7c5ce8"></i>
          </span>
          mini-spring-iteration
        </div>
        <div class="ds-path-body">
          <p>
            参照 Spring 源码的渐进式实现，完整保留 BeanFactory 继承树、三级缓存、
            事件广播与类型转换，配套《手写 Spring：渐进式源码实践》，目标是让你能徒手复刻一遍。
          </p>
          <ul>
            <li>容器启动九步：<code>refresh()</code> 全流程</li>
            <li>三级缓存与提前引用：<code>getEarlyBeanReference</code></li>
            <li>事件广播：<code>ApplicationEventMulticaster</code></li>
            <li>类型转换：<code>ConversionService</code> 体系</li>
          </ul>
          <a class="ds-btn ds-btn--ghost ds-btn--sm" href="./guide/ioc/bootstrap">容器启动全流程 →</a>
        </div>
      </div>
    </div>
  </div>
</section>

<section class="ds-section ds-section--wide">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Combination</span>
    <h2 class="ds-heading">也可以两条都走</h2>
    <p class="ds-sub">最完整的走法是先用 original 建立直觉，再用 iteration 对照源码补细节 —— 总投入约 4 周。</p>
  </div>
  <div class="ds-grid ds-grid--3">
    <div class="ds-mini-card">
      <span class="k">阶段一 · 约 1 周</span>
      <span class="v">original 建立直觉</span>
    </div>
    <div class="ds-mini-card">
      <span class="k">阶段二 · 约 3 周</span>
      <span class="v">iteration 抠细节</span>
    </div>
    <div class="ds-mini-card">
      <span class="k">最终产出</span>
      <span class="v">能徒手复刻一个 Spring</span>
    </div>
  </div>
  <div class="ds-btn-row" style="margin-top: 32px">
    <a class="ds-btn ds-btn--primary" href="./roadmap">查看完整学习路线 →</a>
    <a class="ds-btn ds-btn--ghost" href="./reference">浏览类参考索引 →</a>
  </div>
</section>
