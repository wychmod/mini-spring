---
layout: page
sidebar: false
title: 手写 Spring
---

<DsHero />

<section class="ds-section ds-section--alt">
  <div class="ds-section-inner">
    <DsStatBar :items="[
      { value: 127, label: '个框架类' },
      { value: 5, label: '张架构图' },
      { value: 2, label: '条实现路径' },
      { value: 12, label: '章文档' }
    ]" />
  </div>
</section>

<section class="ds-section">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Features</span>
    <h2 class="ds-heading">一个框架该有的，这里都有</h2>
    <p class="ds-sub">
      每一块能力都对照真实 Spring 的实现路径来写，不是简化版的"玩具"，而是同一条设计思路的完整复刻。
    </p>
  </div>
  <div class="ds-grid ds-grid--3">
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #2e63e7; --ds-card-accent-soft: rgba(46,99,231,.12)">
      <span class="tag">CORE</span>
      <h3>IoC 容器</h3>
      <p>BeanDefinition 注册、refresh 九步启动、preInstantiateSingletons 提前实例化单例，与 Spring 容器行为对齐。</p>
      <a class="ds-card-link" href="./guide/ioc/bootstrap">容器启动全流程 →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #27c9ff; --ds-card-accent-soft: rgba(39,201,255,.12)">
      <span class="tag">DI</span>
      <h3>依赖注入</h3>
      <p>XML property 与 @Autowired / @Value / @Qualifier 注解双通道，在属性填充阶段统一完成注入。</p>
      <a class="ds-card-link" href="./guide/ioc/lifecycle">Bean 生命周期 →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #7c5ce8; --ds-card-accent-soft: rgba(124,92,232,.12)">
      <span class="tag">AOP</span>
      <h3>切面编程</h3>
      <p>AspectJ 表达式切点、JDK 动态代理与 CGLIB 双实现，DefaultAdvisorAutoProxyCreator 自动织入。</p>
      <a class="ds-card-link" href="./guide/aop">AOP 原理与实现 →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #2e63e7; --ds-card-accent-soft: rgba(46,99,231,.12)">
      <span class="tag">CACHE</span>
      <h3>三级缓存</h3>
      <p>singletonFactories 存工厂而非对象，只有真正发生循环依赖时才提前生成代理，完整复现 Spring 设计。</p>
      <a class="ds-card-link" href="./guide/ioc/circular-deps">三级缓存解决循环依赖 →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #27c9ff; --ds-card-accent-soft: rgba(39,201,255,.12)">
      <span class="tag">EVENT</span>
      <h3>事件机制</h3>
      <p>ApplicationEventMulticaster 广播器、ContextRefreshedEvent / ContextClosedEvent 生命周期事件。</p>
      <a class="ds-card-link" href="./guide/advanced/events">事件监听与发布 →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #7c5ce8; --ds-card-accent-soft: rgba(124,92,232,.12)">
      <span class="tag">EXT</span>
      <h3>容器扩展点</h3>
      <p>BeanFactoryPostProcessor 修改定义、BeanPostProcessor 介入创建全程，类型转换服务开箱即用。</p>
      <a class="ds-card-link" href="./reference">在类参考中查看 →</a>
    </article>
  </div>
</section>

<section class="ds-section ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">Two Paths</span>
      <h2 class="ds-heading">两条实现路径，一套 Spring 思想</h2>
      <p class="ds-sub">先建立直觉，再抠细节 —— 两条路径可以顺序走，也可以按你的经验直接选。</p>
    </div>
    <div class="ds-paths ds-paths--with-aside">
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
          <p>29 个框架类实现 Spring 基本功能的极简版本，类名短、链路短，适合第一次建立「Spring 是什么」的直觉。</p>
          <ul>
            <li>注解扫描 + DispatchServlet 的 MVC 实现</li>
            <li>极简 AOP：双代理 + 方法拦截链</li>
            <li>配套阅读《Spring 5 核心原理与 30 个类手写实战》</li>
          </ul>
          <a class="ds-btn ds-btn--ghost ds-btn--sm" href="./compare">进入此路径 →</a>
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
          <p>参照 Spring 源码风格的渐进式实现，分治、抽象与设计模式贯穿始终，本站文档以此为主线讲解。</p>
          <ul>
            <li>BeanFactory 完整继承树与三级缓存</li>
            <li>事件广播、类型转换、注解扫描一应俱全</li>
            <li>配套阅读《手写 Spring：渐进式源码实践》</li>
          </ul>
          <a class="ds-btn ds-btn--ghost ds-btn--sm" href="./guide/preface">进入此路径 →</a>
        </div>
      </div>
      <aside class="ds-aside-card">
        <span class="kicker">不确定选哪个？</span>
        <p class="title">16 个维度逐一对比，30 秒给出结论</p>
        <p class="desc">如果你已有 Spring 使用经验，可以直接从 iteration 开始。</p>
        <a class="ds-btn ds-btn--primary ds-btn--sm" href="./compare">查看完整对比 →</a>
      </aside>
    </div>
  </div>
</section>

<section class="ds-section">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Deep Dive</span>
    <h2 class="ds-heading">每一张图，都与源码逐一核对</h2>
    <p class="ds-sub">5 张架构图全部重绘为矢量 SVG，可放大、可下载，图上的每个类都能在代码里找到。</p>
  </div>
  <DsFigureGallery />
</section>

<section class="ds-section ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">A Glance at the Code</span>
      <h2 class="ds-heading">注释密度高到可以直接当教材读</h2>
      <p class="ds-sub">下面这段就是三级缓存的核心，没有删减 —— 代码里的注释解释了「为什么要这样设计」。</p>
    </div>
    <DsCodeGlance />
  </div>
</section>

<section class="ds-section">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Roadmap</span>
    <h2 class="ds-heading">推荐学习路径</h2>
    <p class="ds-sub">四个阶段，每一步都有明确的产出物和验收标准。</p>
  </div>
  <div class="ds-steps">
    <div class="ds-step">
      <span class="num">01</span>
      <h4>读前言</h4>
      <p>搞清楚为什么手写、两条路径怎么选。</p>
      <span class="time">约 30 分钟</span>
    </div>
    <div class="ds-step">
      <span class="num">02</span>
      <h4>跑通 Demo</h4>
      <p>克隆仓库，运行 iteration 的测试用例。</p>
      <span class="time">约 1 小时</span>
    </div>
    <div class="ds-step">
      <span class="num">03</span>
      <h4>跟主线</h4>
      <p>容器启动 → Bean 生命周期 → 三级缓存。</p>
      <span class="time">约 5.5 小时</span>
    </div>
    <div class="ds-step">
      <span class="num">04</span>
      <h4>进阶</h4>
      <p>AOP 织入与事件机制，对照 Spring 源码。</p>
      <span class="time">约 4 小时</span>
    </div>
  </div>
  <div class="ds-btn-row" style="margin-top: 24px">
    <a class="ds-btn ds-btn--ghost" href="./roadmap">查看完整学习路线 →</a>
  </div>
</section>

<section class="ds-cta">
  <h2>开始你的手写之旅</h2>
  <p>Apache License 2.0 · 欢迎 Star、Issue 与 PR</p>
  <div class="ds-btn-row ds-btn-row--center">
    <a class="ds-btn ds-btn--primary" href="./guide/preface">开始阅读</a>
    <a class="ds-btn ds-btn--ghost" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">
      查看源码
    </a>
  </div>
</section>
