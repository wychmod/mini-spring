---
layout: page
sidebar: false
---

<script setup>
import { withBase } from 'vitepress'
import { ref } from 'vue'

const cloneCmd = 'git clone https://github.com/wychmod/mini-spring.git'
const copied = ref(false)
async function copyClone() {
  try {
    await navigator.clipboard.writeText(cloneCmd)
    copied.value = true
    setTimeout(() => (copied.value = false), 1600)
  } catch {}
}
</script>

<section class="dr-hero">
  <div class="dr-hero-badge"><span class="dot"></span>Open Source · Apache License 2.0 · Java 8</div>
  <h1 class="dr-hero-title">Build <span class="grad">Your Own Spring</span> from Scratch</h1>
  <p class="dr-hero-sub">
    mini-spring ships two implementation paths: a 30-class minimal version that builds your intuition,
    and a progressive, source-code-style version that walks you through the full loop of IoC, DI, AOP,
    events and the three-level cache.
  </p>
  <div class="dr-hero-actions">
    <a class="dr-btn dr-btn-primary" :href="withBase('/en/guide/preface')">Start Reading</a>
    <a class="dr-btn dr-btn-ghost" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">GitHub Repo</a>
  </div>
  <div class="dr-clone">
    <code>{{ cloneCmd }}</code>
    <button type="button" @click="copyClone">{{ copied ? 'Copied' : 'Copy' }}</button>
  </div>
</section>

<section class="dr-section">
  <div class="dr-section-title">Features</div>
  <h2 class="dr-section-heading">Everything a framework should have</h2>
  <div class="dr-features">
    <div class="dr-card" style="--dr-card-accent: #2e63e7">
      <span class="tag">CORE</span>
      <h3>IoC Container</h3>
      <p>BeanDefinition registration, the nine-step refresh bootstrap, preInstantiateSingletons — aligned with how the real Spring container behaves.</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #27c9ff">
      <span class="tag">DI</span>
      <h3>Dependency Injection</h3>
      <p>Two channels converge in one pipeline: XML &lt;property&gt; and @Autowired / @Value / @Qualifier annotations.</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #7c5ce8">
      <span class="tag">AOP</span>
      <h3>Aspect Programming</h3>
      <p>AspectJ pointcut expressions, JDK dynamic proxy and CGLIB, with DefaultAdvisorAutoProxyCreator weaving advice automatically.</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #e5813c">
      <span class="tag">CACHE</span>
      <h3>Three-Level Cache</h3>
      <p>singletonFactories stores factories, not objects — proxies are created early only when circular dependencies actually happen, just like Spring.</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #27a882">
      <span class="tag">EVENT</span>
      <h3>Event Mechanism</h3>
      <p>ApplicationEventMulticaster broadcasting, plus ContextRefreshedEvent and ContextClosedEvent container lifecycle events.</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #e2637a">
      <span class="tag">EXT</span>
      <h3>Extension Points</h3>
      <p>BeanFactoryPostProcessor reshapes definitions, BeanPostProcessor hooks the whole creation flow, and a conversion service comes out of the box.</p>
    </div>
  </div>
</section>

<section class="dr-section">
  <div class="dr-section-title">Two Paths</div>
  <h2 class="dr-section-heading">Two implementations, one set of Spring ideas</h2>
  <div class="dr-paths">
    <div class="dr-path">
      <header>
        <span class="win"><i style="background:#e2637a"></i><i style="background:#e8a23d"></i><i style="background:#27a882"></i></span>
        mini-spring-original
      </header>
      <div class="body">
        <p>A minimal Spring implemented in 30 classes. Short names, short call chains — perfect for building a first intuition of what Spring really is.</p>
        <ul>
          <li>Annotation scanning + a DispatchServlet-based MVC</li>
          <li>Minimal AOP: JDK & CGLIB dual proxies + interception chain</li>
          <li>Companion book: "Spring 5 Core Principles"</li>
        </ul>
      </div>
    </div>
    <div class="dr-path">
      <header>
        <span class="win"><i style="background:#2e63e7"></i><i style="background:#27c9ff"></i><i style="background:#7c5ce8"></i></span>
        mini-spring-iteration
      </header>
      <div class="body">
        <p>A progressive implementation following Spring's source-code style, built on divide-and-conquer, abstraction and design patterns. All docs on this site use it as the main line.</p>
        <ul>
          <li>Full BeanFactory inheritance tree and three-level cache</li>
          <li>Event multicasting, type conversion, annotation scanning</li>
          <li>Companion book: "Handwriting Spring"</li>
        </ul>
      </div>
    </div>
  </div>
</section>

<section class="dr-section">
  <div class="dr-section-title">Deep Dive</div>
  <h2 class="dr-section-heading">Every diagram verified against the source</h2>
  <figure class="dr-figure">
    <img :src="withBase('/images/bean-lifecycle.png')" alt="Bean lifecycle flow diagram" />
    <figcaption>The Bean lifecycle main flow — see the Bean Lifecycle chapter. All 5 architecture diagrams were redrawn as vector SVG.</figcaption>
  </figure>
</section>

<section class="dr-section">
  <div class="dr-section-title">Roadmap</div>
  <h2 class="dr-section-heading">Recommended learning path</h2>
  <div class="dr-steps">
    <div class="dr-step">
      <span class="num">01</span>
      <h4>Read the intro</h4>
      <p>Why hand-write Spring, and which path to pick.</p>
    </div>
    <div class="dr-step">
      <span class="num">02</span>
      <h4>Run the demo</h4>
      <p>Clone the repo and run the iteration test cases.</p>
    </div>
    <div class="dr-step">
      <span class="num">03</span>
      <h4>Follow the main line</h4>
      <p>Bootstrap → Bean lifecycle → three-level cache.</p>
    </div>
    <div class="dr-step">
      <span class="num">04</span>
      <h4>Go deeper</h4>
      <p>AOP weaving and events, compared against real Spring source.</p>
    </div>
  </div>
</section>

<section class="dr-cta">
  <h2>Start building your own Spring</h2>
  <div class="dr-hero-actions">
    <a class="dr-btn dr-btn-primary" :href="withBase('/en/guide/preface')">Start Reading</a>
    <a class="dr-btn dr-btn-ghost" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">View Source</a>
  </div>
</section>
