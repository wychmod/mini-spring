<script setup lang="ts">
/**
 * 文档页统一页头：面包屑 → H1 → 导语（默认插槽，支持 markdown）→ 元信息 → 分隔线
 *
 * 用法（导语写在标签之间，插槽内容会被按 markdown 解析，可保留 **粗体** 与 `行内代码`）：
 *   <DsDocHead title="AOP 原理与实现" group="进阶特性" :meta="['预计阅读 10 分钟']">
 *
 *   AOP 要回答三个问题：**在哪切**、**切什么**、**怎么切**。
 *
 *   </DsDocHead>
 *
 * 无导语时可用自闭合形式：<DsDocHead title="..." group="..." :meta="[...]" />
 */
import { computed, useSlots } from 'vue'
import { useData } from 'vitepress'
import { siteHref } from './siteHref'

const props = withDefaults(
  defineProps<{
    title: string
    /** 面包屑中间层级，如「核心原理」；省略时只显示 首页 / 标题 */
    group?: string
    /** 元信息条目，如 ['预计阅读 8 分钟', '涉及 14 个类'] */
    meta?: string[]
    /** layout: page 的宽版页用窄栏（920px）而非默认 1080px */
    narrow?: boolean
  }>(),
  { group: '', meta: () => [], narrow: false }
)

const slots = useSlots()
const hasLede = computed(() => !!slots.default)

const { lang, frontmatter } = useData()
const zh = computed(() => lang.value.startsWith('zh'))
const homeText = computed(() => (zh.value ? '首页' : 'Home'))
const homeLink = computed(() => siteHref(zh.value ? '/' : '/en/'))

/** layout: page 的宽版页：页头需要自己补留白并对齐 1080px 内容宽 */
const standalone = computed(() => frontmatter.value.layout === 'page')
</script>

<template>
  <header
    class="ds-pagehead ds-pagehead--doc"
    :class="{ 'ds-pagehead--standalone': standalone, 'ds-pagehead--narrow': narrow }"
  >
    <nav class="ds-breadcrumb" :aria-label="zh ? '面包屑' : 'Breadcrumb'">
      <a :href="homeLink">{{ homeText }}</a>
      <template v-if="group">
        <span class="sep">/</span>
        <span>{{ group }}</span>
      </template>
      <span class="sep">/</span>
      <span>{{ title }}</span>
    </nav>

    <h1>{{ title }}</h1>

    <div v-if="hasLede" class="ds-lede">
      <slot />
    </div>

    <div v-if="meta.length" class="ds-docmeta">
      <template v-for="(m, i) in meta" :key="i">
        <span>{{ m }}</span>
        <span v-if="i < meta.length - 1" class="sep">·</span>
      </template>
    </div>
  </header>
</template>
