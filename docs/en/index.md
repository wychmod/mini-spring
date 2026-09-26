---
layout: page
sidebar: false
title: Build Your Own Spring
---

<DsHero />

<section class="ds-section ds-section--alt">
  <div class="ds-section-inner">
    <DsStatBar :items="[
      { value: 127, label: 'framework classes' },
      { value: 5, label: 'architecture diagrams' },
      { value: 2, label: 'implementation paths' },
      { value: 12, label: 'documentation chapters' }
    ]" />
  </div>
</section>

<section class="ds-section">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Features</span>
    <h2 class="ds-heading">Everything a framework should have</h2>
    <p class="ds-sub">
      Every capability follows the same design path as the real Spring — not a toy simplification,
      but a faithful reconstruction of the thinking behind it.
    </p>
  </div>
  <div class="ds-grid ds-grid--3">
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #2e63e7; --ds-card-accent-soft: rgba(46,99,231,.12)">
      <span class="tag">CORE</span>
      <h3>IoC Container</h3>
      <p>BeanDefinition registration, the nine-step refresh bootstrap, preInstantiateSingletons — aligned with how the real Spring container behaves.</p>
      <a class="ds-card-link" href="./guide/ioc/bootstrap">Container bootstrap flow →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #27c9ff; --ds-card-accent-soft: rgba(39,201,255,.12)">
      <span class="tag">DI</span>
      <h3>Dependency Injection</h3>
      <p>Two channels converge in one pipeline: XML property and @Autowired / @Value / @Qualifier annotations, resolved during populate.</p>
      <a class="ds-card-link" href="./guide/ioc/lifecycle">Bean lifecycle →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #7c5ce8; --ds-card-accent-soft: rgba(124,92,232,.12)">
      <span class="tag">AOP</span>
      <h3>Aspect Programming</h3>
      <p>Pointcut expressions, JDK dynamic proxy and CGLIB, with DefaultAdvisorAutoProxyCreator weaving advice automatically.</p>
      <a class="ds-card-link" href="./guide/aop">AOP principles →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #2e63e7; --ds-card-accent-soft: rgba(46,99,231,.12)">
      <span class="tag">CACHE</span>
      <h3>Three-Level Cache</h3>
      <p>singletonFactories stores factories, not objects — proxies are created early only when circular dependencies actually happen.</p>
      <a class="ds-card-link" href="./guide/ioc/circular-deps">Circular dependencies →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #27c9ff; --ds-card-accent-soft: rgba(39,201,255,.12)">
      <span class="tag">EVENT</span>
      <h3>Event Mechanism</h3>
      <p>ApplicationEventMulticaster broadcasting, plus ContextRefreshedEvent and ContextClosedEvent container lifecycle events.</p>
      <a class="ds-card-link" href="./guide/advanced/events">Events →</a>
    </article>
    <article class="ds-card ds-card--hover" style="--ds-card-accent: #7c5ce8; --ds-card-accent-soft: rgba(124,92,232,.12)">
      <span class="tag">EXT</span>
      <h3>Extension Points</h3>
      <p>BeanFactoryPostProcessor reshapes definitions, BeanPostProcessor hooks the creation flow, and a conversion service ships out of the box.</p>
      <a class="ds-card-link" href="./reference">Browse class index →</a>
    </article>
  </div>
</section>

<section class="ds-section ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">Two Paths</span>
      <h2 class="ds-heading">Two implementations, one set of Spring ideas</h2>
      <p class="ds-sub">Build intuition first, then dig into details — walk both in order, or pick one based on your experience.</p>
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
          <p>A minimal Spring in 29 framework classes. Short names, short call chains — perfect for a first intuition of what Spring really is.</p>
          <ul>
            <li>Annotation scanning + a DispatchServlet-based MVC</li>
            <li>Minimal AOP: dual proxies + interception chain</li>
            <li>Companion book: "Spring 5 Core Principles"</li>
          </ul>
          <a class="ds-btn ds-btn--ghost ds-btn--sm" href="./compare">Enter this path →</a>
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
          <p>A progressive implementation following Spring's source-code style, built on divide-and-conquer, abstraction and design patterns. All docs on this site use it as the main line.</p>
          <ul>
            <li>Full BeanFactory inheritance tree and three-level cache</li>
            <li>Event multicasting, type conversion, annotation scanning</li>
            <li>Companion book: "Handwriting Spring"</li>
          </ul>
          <a class="ds-btn ds-btn--ghost ds-btn--sm" href="./guide/preface">Enter this path →</a>
        </div>
      </div>
      <aside class="ds-aside-card">
        <span class="kicker">Not sure which one?</span>
        <p class="title">16 dimensions compared side by side, conclusion in 30 seconds</p>
        <p class="desc">If you already use Spring daily, start straight from iteration.</p>
        <a class="ds-btn ds-btn--primary ds-btn--sm" href="./compare">See full comparison →</a>
      </aside>
    </div>
  </div>
</section>

<section class="ds-section">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Deep Dive</span>
    <h2 class="ds-heading">Every diagram verified against the source</h2>
    <p class="ds-sub">All 5 architecture diagrams were redrawn as vector SVG — zoomable, downloadable, and every class on them exists in the code.</p>
  </div>
  <DsFigureGallery />
</section>

<section class="ds-section ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">A Glance at the Code</span>
      <h2 class="ds-heading">Comment density high enough to read as teaching material</h2>
      <p class="ds-sub">This is the heart of the three-level cache, unabridged — the comments in the code explain the design rationale.</p>
    </div>
    <DsCodeGlance />
  </div>
</section>

<section class="ds-section">
  <div class="ds-section-head">
    <span class="ds-eyebrow">Roadmap</span>
    <h2 class="ds-heading">Recommended learning path</h2>
    <p class="ds-sub">Four stages, each with a concrete output and an acceptance check.</p>
  </div>
  <div class="ds-steps">
    <div class="ds-step">
      <span class="num">01</span>
      <h4>Read the intro</h4>
      <p>Why hand-write Spring, and which path to pick.</p>
      <span class="time">~30 min</span>
    </div>
    <div class="ds-step">
      <span class="num">02</span>
      <h4>Run the demo</h4>
      <p>Clone the repo and run the iteration tests.</p>
      <span class="time">~1 hour</span>
    </div>
    <div class="ds-step">
      <span class="num">03</span>
      <h4>Follow the main line</h4>
      <p>Bootstrap → Bean lifecycle → three-level cache.</p>
      <span class="time">~5.5 hours</span>
    </div>
    <div class="ds-step">
      <span class="num">04</span>
      <h4>Go deeper</h4>
      <p>AOP weaving and events, compared against Spring source.</p>
      <span class="time">~4 hours</span>
    </div>
  </div>
  <div class="ds-btn-row" style="margin-top: 24px">
    <a class="ds-btn ds-btn--ghost" href="./roadmap">See the full roadmap →</a>
  </div>
</section>

<section class="ds-cta">
  <h2>Start building your own Spring</h2>
  <p>Apache License 2.0 · Stars, issues and PRs are welcome</p>
  <div class="ds-btn-row ds-btn-row--center">
    <a class="ds-btn ds-btn--primary" href="./guide/preface">Start reading</a>
    <a class="ds-btn ds-btn--ghost" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">
      View source
    </a>
  </div>
</section>
