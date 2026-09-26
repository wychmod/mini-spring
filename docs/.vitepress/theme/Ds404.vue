<script setup lang="ts">
/**
 * 404 页：居中单栏，双语自适应（按当前语言选择文案）
 */
import { computed } from 'vue'
import { useData } from 'vitepress'
import { siteHref } from './siteHref'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

const t = computed(() =>
  zh.value
    ? {
        code: '404',
        title: '页面走丢了',
        desc: '它可能已被移动或删除，试试下面的入口。',
        home: '回到首页',
        reference: '类参考索引',
        roadmap: '学习路线',
      }
    : {
        code: '404',
        title: 'Page not found',
        desc: 'It may have been moved or removed. Try one of these instead.',
        home: 'Go home',
        reference: 'Class Index',
        roadmap: 'Roadmap',
      }
)
</script>

<template>
  <div class="ds-404">
    <div class="code" aria-hidden="true">{{ t.code }}</div>
    <h1>{{ t.title }}</h1>
    <p>{{ t.desc }}</p>
    <div class="ds-btn-row ds-btn-row--center">
      <a class="ds-btn ds-btn--primary" :href="siteHref(zh ? '/' : '/en/')">{{ t.home }}</a>
      <a class="ds-btn ds-btn--ghost" :href="siteHref(zh ? '/reference' : '/en/reference')">
        {{ t.reference }}
      </a>
      <a class="ds-btn ds-btn--ghost" :href="siteHref(zh ? '/roadmap' : '/en/roadmap')">
        {{ t.roadmap }}
      </a>
    </div>
  </div>
</template>
