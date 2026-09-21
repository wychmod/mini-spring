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
  <div class="dr-hero-badge"><span class="dot"></span>开源项目 · Apache License 2.0 · Java 8</div>
  <h1 class="dr-hero-title">从 0 开始，<span class="grad">手写一个 Spring</span></h1>
  <p class="dr-hero-sub">
    mini-spring 提供两条实现路径：30 个类的极简版帮你建立直觉，源码风格的渐进式版带你走完
    IoC、DI、AOP、事件与三级缓存的完整闭环。
  </p>
  <div class="dr-hero-actions">
    <a class="dr-btn dr-btn-primary" :href="withBase('/guide/preface')">开始阅读</a>
    <a class="dr-btn dr-btn-ghost" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">GitHub 仓库</a>
  </div>
  <div class="dr-clone">
    <code>{{ cloneCmd }}</code>
    <button type="button" @click="copyClone">{{ copied ? '已复制' : '复制' }}</button>
  </div>
</section>

<section class="dr-section">
  <div class="dr-section-title">Features</div>
  <h2 class="dr-section-heading">一个框架该有的，这里都有</h2>
  <div class="dr-features">
    <div class="dr-card" style="--dr-card-accent: #2e63e7">
      <span class="tag">CORE</span>
      <h3>IoC 容器</h3>
      <p>BeanDefinition 注册、refresh 九步启动、preInstantiateSingletons 提前实例化单例，与 Spring 容器行为对齐。</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #27c9ff">
      <span class="tag">DI</span>
      <h3>依赖注入</h3>
      <p>XML property 与 @Autowired / @Value / @Qualifier 注解双通道，属性填充阶段统一完成注入。</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #7c5ce8">
      <span class="tag">AOP</span>
      <h3>切面编程</h3>
      <p>AspectJ 表达式切点、JDK 动态代理与 CGLIB 双实现，DefaultAdvisorAutoProxyCreator 自动织入。</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #e5813c">
      <span class="tag">CACHE</span>
      <h3>三级缓存</h3>
      <p>singletonFactories 存工厂而非对象，只有真正发生循环依赖时才提前生成代理，完整复现 Spring 设计。</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #27a882">
      <span class="tag">EVENT</span>
      <h3>事件机制</h3>
      <p>ApplicationEventMulticaster 广播器、ContextRefreshedEvent / ContextClosedEvent 生命周期事件。</p>
    </div>
    <div class="dr-card" style="--dr-card-accent: #e2637a">
      <span class="tag">EXT</span>
      <h3>容器扩展点</h3>
      <p>BeanFactoryPostProcessor 修改定义、BeanPostProcessor 介入创建全程，类型转换服务开箱即用。</p>
    </div>
  </div>
</section>

<section class="dr-section">
  <div class="dr-section-title">Two Paths</div>
  <h2 class="dr-section-heading">两条实现路径，一套 Spring 思想</h2>
  <div class="dr-paths">
    <div class="dr-path">
      <header>
        <span class="win"><i style="background:#e2637a"></i><i style="background:#e8a23d"></i><i style="background:#27a882"></i></span>
        mini-spring-original
      </header>
      <div class="body">
        <p>30 个类实现 Spring 基本功能的极简版本，类名短、链路短，适合第一次建立“Spring 是什么”的直觉。</p>
        <ul>
          <li>注解扫描 + DispatchServlet 的 MVC 实现</li>
          <li>极简 AOP：CGLIB 代理 + 方法拦截链</li>
          <li>配套阅读《Spring 5 核心原理与 30 个类手写实战》</li>
        </ul>
      </div>
    </div>
    <div class="dr-path">
      <header>
        <span class="win"><i style="background:#2e63e7"></i><i style="background:#27c9ff"></i><i style="background:#7c5ce8"></i></span>
        mini-spring-iteration
      </header>
      <div class="body">
        <p>参照 Spring 源码风格的渐进式实现，分治、抽象与设计模式贯穿始终，本站文档以此为主线讲解。</p>
        <ul>
          <li>BeanFactory 完整继承树与三级缓存</li>
          <li>事件广播、类型转换、注解扫描一应俱全</li>
          <li>配套阅读《手写 Spring：渐进式源码实践》</li>
        </ul>
      </div>
    </div>
  </div>
</section>

<section class="dr-section">
  <div class="dr-section-title">Deep Dive</div>
  <h2 class="dr-section-heading">每一张图，都与源码逐一核对</h2>
  <figure class="dr-figure">
    <img :src="withBase('/images/bean-lifecycle.png')" alt="Bean 生命周期流程图" />
    <figcaption>Bean 生命周期主流程 —— 见文档《Bean 生命周期》章节，全部 5 张架构图均已重绘为矢量 SVG。</figcaption>
  </figure>
</section>

<section class="dr-section">
  <div class="dr-section-title">Roadmap</div>
  <h2 class="dr-section-heading">推荐学习路径</h2>
  <div class="dr-steps">
    <div class="dr-step">
      <span class="num">01</span>
      <h4>读前言</h4>
      <p>搞清楚为什么手写、两条路径怎么选。</p>
    </div>
    <div class="dr-step">
      <span class="num">02</span>
      <h4>跑通 Demo</h4>
      <p>克隆仓库，运行 iteration 的测试用例。</p>
    </div>
    <div class="dr-step">
      <span class="num">03</span>
      <h4>跟主线</h4>
      <p>容器启动 → Bean 生命周期 → 三级缓存。</p>
    </div>
    <div class="dr-step">
      <span class="num">04</span>
      <h4>进阶</h4>
      <p>AOP 织入与事件机制，对照 Spring 源码。</p>
    </div>
  </div>
</section>

<section class="dr-cta">
  <h2>开始你的手写之旅</h2>
  <div class="dr-hero-actions">
    <a class="dr-btn dr-btn-primary" :href="withBase('/guide/preface')">开始阅读</a>
    <a class="dr-btn dr-btn-ghost" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">查看源码</a>
  </div>
</section>
