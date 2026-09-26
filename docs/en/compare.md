---
layout: page
sidebar: false
title: Which implementation path to choose
description: Compare mini-spring-original and mini-spring-iteration across 16 dimensions
---

<section class="ds-section ds-section--wide">
  <header class="ds-pagehead" style="padding-top: 0">
    <nav class="ds-breadcrumb" aria-label="Breadcrumb">
      <a :href="$href('/en/')">Home</a>
      <span>/</span>
      <span>Compare</span>
    </nav>
    <h1>Two implementation paths — which one?</h1>
    <p class="ds-lede">
      mini-spring teaches the same Spring ideas through two modules, but their pace, naming and
      coverage are quite different. This page lays the differences out in a 16-dimension table so
      you can decide where to start in one read.
    </p>
  </header>
  <div class="ds-callout" style="margin-bottom: 40px">
    <span class="label">30-second verdict</span>
    <p>Start with <b>mini-spring-original</b> to build intuition (about 1 week), then move to <b>mini-spring-iteration</b> for the details.</p>
    <p>If you already use Spring daily, jump straight into iteration — skipping original costs you nothing.</p>
  </div>
  <h2 class="ds-heading" style="margin-bottom: 16px">Dimension-by-dimension</h2>
  <DsCompare />
</section>

<section class="ds-section ds-section--wide">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Decision</span>
    <h2 class="ds-heading">Two questions to find your start</h2>
    <p class="ds-sub">You don't need the whole table — answering these two is enough.</p>
  </div>
  <div class="ds-grid ds-grid--2">
    <article class="ds-card" style="--ds-card-accent: #2e63e7; --ds-card-accent-soft: rgba(46,99,231,.12)">
      <span class="tag">Q1</span>
      <h3>Do you want intuition fast — "what is Spring, really?"</h3>
      <p>
        First contact, interview prep, or just wiring up the main flow — pick original. Its 29 classes
        connect IoC, DI and AOP into the shortest possible chain, so nothing gets in the way.
      </p>
      <a class="ds-card-link" :href="$href('/en/guide/getting-started')">Start with original →</a>
    </article>
    <article class="ds-card" style="--ds-card-accent: #7c5ce8; --ds-card-accent-soft: rgba(124,92,232,.12)">
      <span class="tag">Q2</span>
      <h3>Do you already have Spring experience?</h3>
      <p>
        Used <code>@Autowired</code>, written aspects, been grilled on the three-level cache — go straight
        to iteration. Same names, same layering as Spring source, 98 classes compared section by section.
      </p>
      <a class="ds-card-link" :href="$href('/en/guide/preface')">Start with iteration →</a>
    </article>
  </div>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">In Detail</span>
      <h2 class="ds-heading">What each path uniquely offers</h2>
      <p class="ds-sub">Beyond the shared IoC and AOP chapters, each path has content only it covers.</p>
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
            An annotation-scanning minimal container with short class names and short call chains,
            paired with "Spring 5 Core Principles". Its goal is a week-long intuition sprint.
          </p>
          <ul>
            <li>Annotation-driven container: <code>@Component</code> straight to <code>getBean</code></li>
            <li>Minimal MVC: <code>DispatchServlet</code> + <code>HandlerMapping</code></li>
            <li>Interception-chain AOP: dual proxies + before advice</li>
            <li>Single-level cache demo of circular dependencies: <code>CircleTest</code></li>
          </ul>
          <a class="ds-btn ds-btn--ghost ds-btn--sm" :href="$href('/en/guide/getting-started')">Setup &amp; Run →</a>
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
            A progressive implementation in the style of Spring's own source, keeping the full
            BeanFactory hierarchy, three-level cache, event multicasting and type conversion.
          </p>
          <ul>
            <li>Nine-step bootstrap: the full <code>refresh()</code> flow</li>
            <li>Three-level cache and early references: <code>getEarlyBeanReference</code></li>
            <li>Event multicasting: <code>ApplicationEventMulticaster</code></li>
            <li>Type conversion: the <code>ConversionService</code> system</li>
          </ul>
          <a class="ds-btn ds-btn--ghost ds-btn--sm" :href="$href('/en/guide/ioc/bootstrap')">Container Bootstrap Flow →</a>
        </div>
      </div>
    </div>
  </div>
</section>

<section class="ds-section ds-section--wide">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Combination</span>
    <h2 class="ds-heading">You can also walk both</h2>
    <p class="ds-sub">The most complete route starts with original for intuition, then uses iteration to fill in the details — about 4 weeks total.</p>
  </div>
  <div class="ds-grid ds-grid--3">
    <div class="ds-mini-card">
      <span class="k">Phase 1 · ~1 week</span>
      <span class="v">original — build intuition</span>
    </div>
    <div class="ds-mini-card">
      <span class="k">Phase 2 · ~3 weeks</span>
      <span class="v">iteration — dig into details</span>
    </div>
    <div class="ds-mini-card">
      <span class="k">Final outcome</span>
      <span class="v">Rebuild Spring from scratch</span>
    </div>
  </div>
  <div class="ds-btn-row" style="margin-top: 32px">
    <a class="ds-btn ds-btn--primary" :href="$href('/en/roadmap')">Full learning roadmap →</a>
    <a class="ds-btn ds-btn--ghost" :href="$href('/en/reference')">Browse the class index →</a>
  </div>
</section>
