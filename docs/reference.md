---
layout: page
sidebar: false
title: 类参考索引
description: 127 个框架类，按模块与包分组，支持即时搜索与筛选
---

<section class="ds-section ds-section--wide">
  <header class="ds-pagehead" style="padding-top: 0">
    <nav class="ds-breadcrumb" aria-label="面包屑">
      <a href="./">首页</a>
      <span>/</span>
      <span>类参考索引</span>
    </nav>
    <h1>类参考索引</h1>
    <p class="ds-lede">
      127 个框架类，按模块与包分组，可即时筛选。每个类都有一句话职责说明，
      有关联章节的还会给出直达链接 —— 这是整个项目的「地图」。
    </p>
  </header>
  <div class="ds-grid ds-grid--4" style="margin-bottom: 40px">
    <div class="ds-mini-card">
      <span class="k">框架类总数</span>
      <span class="v mono">127</span>
    </div>
    <div class="ds-mini-card">
      <span class="k">mini-spring-iteration</span>
      <span class="v mono">98</span>
    </div>
    <div class="ds-mini-card">
      <span class="k">mini-spring-original</span>
      <span class="v mono">29</span>
    </div>
    <div class="ds-mini-card">
      <span class="k">包分组（模块 · 包）</span>
      <span class="v mono">10</span>
    </div>
  </div>
  <div class="ds-callout" style="margin-bottom: 32px">
    <span class="label">怎么用</span>
    <p>
      按类名搜索，或按模块 / 包筛选。命中关键词会高亮，分组可折叠；
      搜索或筛选时会自动展开全部分组，方便你横向比较同一层的实现。
    </p>
  </div>
  <DsClassIndex />
</section>

<section class="ds-section ds-section--wide ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">Layering</span>
      <h2 class="ds-heading">十个包分组，各管一摊</h2>
      <p class="ds-sub">iteration 分 6 个分层包、original 分 4 个功能包，职责边界清晰。</p>
    </div>
    <h3 class="ds-heading" style="font-size: 17px; margin-bottom: 16px">mini-spring-iteration · 6 个分层包</h3>
    <div class="ds-grid ds-grid--3" style="margin-bottom: 40px">
      <article class="ds-card">
        <span class="tag">beans · 41</span>
        <h3>Bean 工厂与生命周期</h3>
        <p>BeanDefinition 注册、BeanFactory 继承树、三级缓存、后置处理器与生命周期回调。</p>
      </article>
      <article class="ds-card">
        <span class="tag">context · 21</span>
        <h3>容器入口与事件</h3>
        <p>ApplicationContext、refresh() 九步、注解扫描器与事件广播器。</p>
      </article>
      <article class="ds-card">
        <span class="tag">aop · 18</span>
        <h3>切面与代理</h3>
        <p>切点表达式解析、通知、JDK / CGLIB 双代理与 Advisor 自动织入。</p>
      </article>
      <article class="ds-card">
        <span class="tag">core · 14</span>
        <h3>基础抽象</h3>
        <p>Resource 资源抽象与 ConversionService 类型转换体系。</p>
      </article>
      <article class="ds-card">
        <span class="tag">stereotype · 1</span>
        <h3>构造型注解</h3>
        <p>@Component 等构造型注解的定义。</p>
      </article>
      <article class="ds-card">
        <span class="tag">utils · 3</span>
        <h3>工具类</h3>
        <p>反射工具与数字工具，供容器内部调用。</p>
      </article>
    </div>
    <h3 class="ds-heading" style="font-size: 17px; margin-bottom: 16px">mini-spring-original · 4 个功能包</h3>
    <div class="ds-grid ds-grid--4">
      <article class="ds-card">
        <span class="tag">aop · 14</span>
        <h3>切面与代理</h3>
        <p>自研 AOP 拦截链、Advisor 配置与 CGLIB 代理工厂。</p>
      </article>
      <article class="ds-card">
        <span class="tag">webmvc · 7</span>
        <h3>极简 MVC</h3>
        <p>DispatchServlet、HandlerMapping / Adapter 与视图解析。</p>
      </article>
      <article class="ds-card">
        <span class="tag">beans · 3</span>
        <h3>Bean 基础</h3>
        <p>BeanDefinition、BeanDefinitionReader 与 BeanWrapper。</p>
      </article>
      <article class="ds-card">
        <span class="tag">annotation · 5</span>
        <h3>注解定义</h3>
        <p>@Controller / @Service / @Autowired 等注解。</p>
      </article>
    </div>
    <div class="ds-btn-row" style="margin-top: 32px">
      <a class="ds-btn ds-btn--ghost" href="./guide/start/structure">目录结构导览 →</a>
      <a class="ds-btn ds-btn--ghost" href="./guide/ioc/class-diagram">IoC 全景类图 →</a>
    </div>
  </div>
</section>
