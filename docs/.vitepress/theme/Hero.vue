<script setup lang="ts">
/** Hero：左侧文案 + 右侧终端窗口 + 克隆命令复制条 */
import { computed, ref } from 'vue'
import { useData } from 'vitepress'
import { siteHref } from './siteHref'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

const cloneCmd = 'git clone https://github.com/wychmod/mini-spring.git'
const copied = ref(false)

async function copyClone() {
  try {
    await navigator.clipboard.writeText(cloneCmd)
    copied.value = true
    setTimeout(() => (copied.value = false), 1600)
  } catch {
    /* 剪贴板不可用时静默降级 */
  }
}
</script>

<template>
  <section class="ds-hero">
    <div class="ds-hero-inner">
      <div class="ds-hero-copy">
        <div class="ds-hero-badge">
          <span class="dot"></span>
          {{ zh ? '开源项目 · Apache License 2.0 · Java 8' : 'Open Source · Apache License 2.0 · Java 8' }}
        </div>

        <h1 class="ds-hero-title">
          <template v-if="zh">从 0 开始，<span class="grad">手写一个 Spring</span></template>
          <template v-else>Build <span class="grad">Your Own Spring</span> from Scratch</template>
        </h1>

        <p class="ds-hero-sub">
          <template v-if="zh">
            mini-spring 提供两条实现路径：30 个类的极简版帮你建立直觉，源码风格的渐进式版带你走完
            IoC、DI、AOP、事件与三级缓存的完整闭环。
          </template>
          <template v-else>
            mini-spring ships two implementation paths: a 30-class minimal version that builds your
            intuition, and a progressive, source-code-style version covering the full loop of IoC,
            DI, AOP, events and the three-level cache.
          </template>
        </p>

        <p class="ds-hero-meta">
          {{
            zh
              ? '5 张架构图 · 127 个类 · 双语文档'
              : '5 architecture diagrams · 127 classes · bilingual docs'
          }}
        </p>

        <div class="ds-btn-row">
          <a class="ds-btn ds-btn--primary" :href="siteHref(zh ? '/guide/preface' : '/en/guide/preface')">
            {{ zh ? '开始阅读' : 'Start Reading' }}
          </a>
          <a
            class="ds-btn ds-btn--ghost"
            href="https://github.com/wychmod/mini-spring"
            target="_blank"
            rel="noreferrer"
          >
            {{ zh ? 'GitHub 仓库' : 'GitHub Repo' }}
          </a>
        </div>

        <div class="ds-clone">
          <code>{{ cloneCmd }}</code>
          <button type="button" :class="{ done: copied }" @click="copyClone">
            {{ copied ? (zh ? '已复制' : 'Copied') : zh ? '复制' : 'Copy' }}
          </button>
        </div>
      </div>

      <div class="ds-term">
        <div class="ds-term-bar">
          <span class="dots">
            <i style="background: #e2637a"></i>
            <i style="background: #e8a23d"></i>
            <i style="background: #27a882"></i>
          </span>
          <span class="name">~/mini-spring</span>
        </div>
        <div class="ds-term-body">
          <div><span class="prompt">$</span> <span class="cmd">git clone wychmod/mini-spring</span></div>
          <div><span class="prompt">$</span> <span class="cmd">cd mini-spring</span></div>
          <div><span class="prompt">$</span> <span class="cmd">mvn clean test</span></div>
          <div class="out">[INFO] Tests run: 2, Failures: 0</div>
          <div class="out">[INFO] BUILD SUCCESS</div>
          <div>&nbsp;</div>
          <div><span class="prompt">$</span> <span class="cmd">tree -L 1</span></div>
          <div class="out">mini-spring-original</div>
          <div class="out">mini-spring-iteration</div>
          <div class="out">docs</div>
        </div>
      </div>
    </div>
  </section>
</template>
