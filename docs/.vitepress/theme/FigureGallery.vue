<script setup lang="ts">
/** 架构图预览：主图 + 4 缩略图 + 灯箱 + 下载 SVG */
import { computed, onMounted, onUnmounted, ref } from 'vue'
import { useData } from 'vitepress'
import { siteHref } from './siteHref'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

const figs = computed(() => [
  {
    src: '/images/bean-lifecycle.png',
    svg: '/images/bean-lifecycle.svg',
    zh: 'Bean 生命周期',
    en: 'Bean Lifecycle',
  },
  {
    src: '/images/class-loading-flow.png',
    svg: '/images/class-loading-flow.svg',
    zh: '类加载全流程',
    en: 'Class Loading Flow',
  },
  {
    src: '/images/ioc-class-diagram.png',
    svg: '/images/ioc-class-diagram.svg',
    zh: 'IoC 全景类图',
    en: 'IoC Class Diagram',
  },
  {
    src: '/images/aop-class-diagram.png',
    svg: '/images/aop-class-diagram.svg',
    zh: 'AOP 类图',
    en: 'AOP Class Diagram',
  },
  {
    src: '/images/three-level-cache.png',
    svg: '/images/three-level-cache.svg',
    zh: '三级缓存',
    en: 'Three-Level Cache',
  },
])

const active = ref(0)
const boxOpen = ref(false)

function close() {
  boxOpen.value = false
}

function onKey(e: KeyboardEvent) {
  if (e.key === 'Escape') close()
}

onMounted(() => window.addEventListener('keydown', onKey))
onUnmounted(() => window.removeEventListener('keydown', onKey))

const cur = computed(() => figs.value[active.value])
const mainSrc = computed(() => siteHref(cur.value.src))
const curSvg = computed(() => siteHref(cur.value.svg))
</script>

<template>
  <div>
    <figure class="ds-figure">
      <div class="ds-figure-tools">
        <a :href="mainSrc" target="_blank" rel="noreferrer" :title="zh ? '在新标签打开' : 'Open in new tab'">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
            <path d="M14 4h6v6M20 4l-8 8M18 14v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h5" />
          </svg>
        </a>
        <a :href="curSvg" download :title="zh ? '下载 SVG' : 'Download SVG'">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
            <path d="M12 3v12m0 0 4-4m-4 4-4-4M4 19h16" />
          </svg>
        </a>
      </div>

      <div class="ds-figure-stage">
        <img :src="mainSrc" :alt="zh ? cur.zh : cur.en" @click="boxOpen = true" />
      </div>

      <figcaption>
        {{
          zh
            ? `${cur.zh} —— 全部架构图均重绘为矢量 SVG，可与源码逐一核对。`
            : `${cur.en} — every diagram is a vector SVG, verifiable against the source.`
        }}
      </figcaption>
    </figure>

    <div class="ds-thumbs" role="tablist">
      <button
        v-for="(f, i) in figs"
        :key="f.src"
        class="ds-thumb"
        type="button"
        role="tab"
        :aria-selected="active === i"
        @click="active = i"
      >
        <img :src="siteHref(f.src)" :alt="zh ? f.zh : f.en" />
        <span class="label">{{ zh ? f.zh : f.en }}</span>
      </button>
    </div>

    <Teleport to="body">
      <div v-if="boxOpen" class="ds-lightbox" @click="close">
        <img :src="mainSrc" :alt="zh ? cur.zh : cur.en" />
      </div>
    </Teleport>
  </div>
</template>
