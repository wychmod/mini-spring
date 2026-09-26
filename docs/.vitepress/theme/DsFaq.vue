<script setup lang="ts">
/**
 * FAQ：可搜索的折叠面板，按主题分组
 * 搜索无结果时给出空状态；命中关键词在问题与答案中高亮。
 */
import { computed, ref } from 'vue'
import { useData } from 'vitepress'
import { faq } from '../data/faq'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

const q = ref('')
const open = ref<Record<string, boolean>>({})

const groups = computed(() => {
  const kw = q.value.trim().toLowerCase()
  if (!kw) return faq
  return faq
    .map((g) => ({
      ...g,
      items: g.items.filter((it) =>
        (zh.value ? it.q.zh + it.a.zh : it.q.en + it.a.en).toLowerCase().includes(kw)
      ),
    }))
    .filter((g) => g.items.length)
})

const total = computed(() => groups.value.reduce((n, g) => n + g.items.length, 0))
const allCount = computed(() => faq.reduce((n, g) => n + g.items.length, 0))

function key(g: string, i: number) {
  return `${g}#${i}`
}

function isOpen(g: string, i: number) {
  if (q.value.trim()) return true
  return !!open.value[key(g, i)]
}

function toggle(g: string, i: number) {
  const k = key(g, i)
  open.value = { ...open.value, [k]: !isOpen(g, i) }
}

function hl(text: string) {
  const kw = q.value.trim()
  if (!kw) return text
  return text.replace(
    new RegExp(`(${kw.replace(/[.*+?^${}()|[\]\\]/g, '\\$&')})`, 'ig'),
    '<mark>$1</mark>'
  )
}
</script>

<template>
  <div class="ds-refbar">
    <input
      type="search"
      :value="q"
      @input="q = ($event.target as HTMLInputElement).value"
      :placeholder="zh ? '输入问题关键词，例如 三级缓存' : 'Type a keyword, e.g. cache'"
      :aria-label="zh ? '搜索常见问题' : 'Search FAQ'"
    />
    <span class="count">{{ total }} / {{ allCount }}</span>
  </div>

  <div v-if="groups.length === 0" class="ds-refempty">
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.25">
      <circle cx="11" cy="11" r="7" />
      <path d="m20 20-3.5-3.5" />
    </svg>
    <p>{{ zh ? '没有匹配的问题' : 'No matching questions' }}</p>
    <button class="ds-btn ds-btn--ghost ds-btn--sm" type="button" @click="q = ''">
      {{ zh ? '清除筛选' : 'Clear' }}
    </button>
  </div>

  <template v-else>
    <section v-for="g in groups" :key="g.title.zh" class="ds-faq-group">
      <h2 class="ds-faq-heading">{{ zh ? g.title.zh : g.title.en }}</h2>
      <div class="ds-accordion">
        <div v-for="(it, i) in g.items" :key="key(g.title.zh, i)" class="ds-acc-item">
          <button
            class="ds-acc-trigger"
            type="button"
            :aria-expanded="isOpen(g.title.zh, i)"
            @click="toggle(g.title.zh, i)"
          >
            <span v-html="hl(zh ? it.q.zh : it.q.en)"></span>
            <svg class="chev" viewBox="0 0 16 16" fill="none" stroke="currentColor" stroke-width="1.5">
              <path d="m6 3.5 5 4.5-5 4.5" />
            </svg>
          </button>
          <div v-show="isOpen(g.title.zh, i)" class="ds-acc-panel" v-html="hl(zh ? it.a.zh : it.a.en)"></div>
        </div>
      </div>
    </section>
  </template>
</template>
