---
layout: page
sidebar: false
title: Class Reference Index
description: 127 framework classes grouped by module and package, with instant search and filtering
---

<section class="ds-section ds-section--wide">
  <header class="ds-pagehead" style="padding-top: 0">
    <nav class="ds-breadcrumb" aria-label="Breadcrumb">
      <a :href="$href('/en/')">Home</a>
      <span>/</span>
      <span>Reference</span>
    </nav>
    <h1>Class Reference Index</h1>
    <p class="ds-lede">
      127 framework classes grouped by module and package, with instant filtering. Every class has a
      one-line responsibility, and those tied to a chapter link straight to it — this is the project's map.
    </p>
  </header>
  <div class="ds-grid ds-grid--4" style="margin-bottom: 40px">
    <div class="ds-mini-card">
      <span class="k">Framework classes</span>
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
      <span class="k">Package groups</span>
      <span class="v mono">10</span>
    </div>
  </div>
  <div class="ds-callout" style="margin-bottom: 32px">
    <span class="label">How to use</span>
    <p>
      Search by class name, or filter by module / package. Keyword hits are highlighted and groups
      collapse; searching or filtering expands every group so you can compare a layer side by side.
    </p>
  </div>
  <DsClassIndex />
</section>

<section class="ds-section ds-section--wide ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">Layering</span>
      <h2 class="ds-heading">Ten package groups, each with a job</h2>
      <p class="ds-sub">iteration splits into 6 layered packages, original into 4 functional packages.</p>
    </div>
    <h3 class="ds-heading" style="font-size: 17px; margin-bottom: 16px">mini-spring-iteration · 6 layered packages</h3>
    <div class="ds-grid ds-grid--3" style="margin-bottom: 40px">
      <article class="ds-card">
        <span class="tag">beans · 41</span>
        <h3>Bean factory &amp; lifecycle</h3>
        <p>Definition registry, the BeanFactory hierarchy, three-level cache, post-processors and lifecycle callbacks.</p>
      </article>
      <article class="ds-card">
        <span class="tag">context · 21</span>
        <h3>Container entry &amp; events</h3>
        <p>ApplicationContext, the nine-step refresh(), annotation scanning and event multicasting.</p>
      </article>
      <article class="ds-card">
        <span class="tag">aop · 18</span>
        <h3>Aspects &amp; proxies</h3>
        <p>Pointcut expression parsing, advice, JDK / CGLIB proxies and Advisor auto-weaving.</p>
      </article>
      <article class="ds-card">
        <span class="tag">core · 14</span>
        <h3>Core abstractions</h3>
        <p>The Resource abstraction and the ConversionService type-conversion system.</p>
      </article>
      <article class="ds-card">
        <span class="tag">stereotype · 1</span>
        <h3>Stereotype annotation</h3>
        <p>The @Component stereotype annotation definition.</p>
      </article>
      <article class="ds-card">
        <span class="tag">utils · 3</span>
        <h3>Utilities</h3>
        <p>Reflection and number utilities used internally by the container.</p>
      </article>
    </div>
    <h3 class="ds-heading" style="font-size: 17px; margin-bottom: 16px">mini-spring-original · 4 functional packages</h3>
    <div class="ds-grid ds-grid--4">
      <article class="ds-card">
        <span class="tag">aop · 14</span>
        <h3>Aspects &amp; proxies</h3>
        <p>Hand-rolled AOP interception chain, advisor config and CGLIB proxy factory.</p>
      </article>
      <article class="ds-card">
        <span class="tag">webmvc · 7</span>
        <h3>Minimal MVC</h3>
        <p>DispatchServlet, HandlerMapping / Adapter and view resolving.</p>
      </article>
      <article class="ds-card">
        <span class="tag">beans · 3</span>
        <h3>Bean basics</h3>
        <p>BeanDefinition, BeanDefinitionReader and BeanWrapper.</p>
      </article>
      <article class="ds-card">
        <span class="tag">annotation · 5</span>
        <h3>Annotations</h3>
        <p>@Controller / @Service / @Autowired and friends.</p>
      </article>
    </div>
    <div class="ds-btn-row" style="margin-top: 32px">
      <a class="ds-btn ds-btn--ghost" :href="$href('/en/guide/start/structure')">Project Structure →</a>
      <a class="ds-btn ds-btn--ghost" :href="$href('/en/guide/ioc/class-diagram')">IoC Class Diagram →</a>
    </div>
  </div>
</section>
