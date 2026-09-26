<script setup lang="ts">
import { computed, ref } from 'vue'
import { useData } from 'vitepress'
import { siteHref } from './siteHref'
import { registry, pkgNotes, pkgOrder, chapters, chapterLabels } from '../data/classes'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

const q = ref('')
const mod = ref<'all' | 'iteration' | 'original'>('iteration')
const pkg = ref('all')
const open = ref<Record<string, boolean>>({
  'iteration/beans': true,
  'iteration/context': true,
  'iteration/aop': false,
  'iteration/core': false,
  'iteration/stereotype': false,
  'iteration/utils': false,
  'original/webmvc': false,
  'original/annotation': false,
  'original/beans': false,
  'original/aop': false,
})

const pkgOptions = computed(() => {
  const set = new Set(registry.filter((c) => mod.value === 'all' || c.module === mod.value).map((c) => c.pkg))
  return [...set].sort((a, b) => pkgOrder.indexOf(a) - pkgOrder.indexOf(b))
})

const filtered = computed(() => {
  const kw = q.value.trim().toLowerCase()
  return registry.filter((c) => {
    if (mod.value !== 'all' && c.module !== mod.value) return false
    if (pkg.value !== 'all' && c.pkg !== pkg.value) return false
    if (!kw) return true
    return (
      c.name.toLowerCase().includes(kw) ||
      c.desc.zh.toLowerCase().includes(kw) ||
      c.desc.en.toLowerCase().includes(kw) ||
      c.pkg.includes(kw)
    )
  })
})

/** 命中关键词高亮 */
function hl(text: string) {
  const kw = q.value.trim()
  if (!kw) return esc(text)
  const i = text.toLowerCase().indexOf(kw.toLowerCase())
  if (i < 0) return esc(text)
  return (
    esc(text.slice(0, i)) +
    '<mark>' +
    esc(text.slice(i, i + kw.length)) +
    '</mark>' +
    esc(text.slice(i + kw.length))
  )
}

function esc(s: string) {
  return s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;')
}

/** 按模块+包分组 */
const groups = computed(() => {
  const map = new Map<string, typeof registry>()
  for (const c of filtered.value) {
    const key = `${c.module}/${c.pkg}`
    if (!map.has(key)) map.set(key, [])
    map.get(key)!.push(c)
  }
  return [...map.entries()]
    .map(([key, items]) => {
      const [m, p] = key.split('/')
      return { key, module: m, pkg: p, items: items.sort((a, b) => a.name.localeCompare(b.name)) }
    })
    .sort((a, b) => {
      if (a.module !== b.module) return a.module === 'iteration' ? -1 : 1
      return pkgOrder.indexOf(a.pkg) - pkgOrder.indexOf(b.pkg)
    })
})

function isOpen(key: string) {
  // 搜索或筛选时强制展开
  if (q.value.trim() || pkg.value !== 'all') return true
  return open.value[key] !== false
}

function toggle(key: string) {
  open.value = { ...open.value, [key]: !isOpen(key) }
}

function clearAll() {
  q.value = ''
  pkg.value = 'all'
}

const total = computed(() => filtered.value.length)
</script>

<template>
  <div class="ds-refbar">
    <input
      type="search"
      :value="q"
      @input="q = ($event.target as HTMLInputElement).value"
      :placeholder="zh ? '输入类名或职责关键词' : 'Search class name or responsibility'"
      :aria-label="zh ? '搜索类' : 'Search classes'"
    />
    <select v-model="mod" :aria-label="zh ? '模块筛选' : 'Filter by module'">
      <option value="iteration">mini-spring-iteration</option>
      <option value="original">mini-spring-original</option>
      <option value="all">{{ zh ? '全部模块' : 'All modules' }}</option>
    </select>
    <select v-model="pkg" :aria-label="zh ? '包筛选' : 'Filter by package'">
      <option value="all">{{ zh ? '全部包' : 'All packages' }}</option>
      <option v-for="p in pkgOptions" :key="p" :value="p">{{ p }}</option>
    </select>
    <span class="count">{{ total }} / {{ registry.length }}</span>
  </div>

  <div v-if="groups.length === 0" class="ds-refempty">
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.25">
      <circle cx="11" cy="11" r="7" />
      <path d="m20 20-3.5-3.5" />
    </svg>
    <p>{{ zh ? '没有匹配的类' : 'No matching classes' }}</p>
    <button class="ds-btn ds-btn--ghost ds-btn--sm" type="button" @click="clearAll">
      {{ zh ? '清除筛选' : 'Clear filters' }}
    </button>
  </div>

  <section v-for="g in groups" :key="g.key" class="ds-refgroup">
    <button
      class="ds-refgroup-head"
      type="button"
      :aria-expanded="isOpen(g.key)"
      @click="toggle(g.key)"
    >
      <svg class="chev" viewBox="0 0 16 16" fill="none" stroke="currentColor" stroke-width="1.5">
        <path d="m6 3.5 5 4.5-5 4.5" />
      </svg>
      <span class="name">{{ g.module }} / {{ g.pkg }}/</span>
      <span class="count">{{ g.items.length }} {{ zh ? '个类' : 'classes' }}</span>
      <span v-if="pkgNotes[g.pkg]" class="count" style="margin-left: auto">
        {{ zh ? pkgNotes[g.pkg].zh : pkgNotes[g.pkg].en }}
      </span>
    </button>

    <table v-if="isOpen(g.key)" class="ds-reftable">
      <thead>
        <tr>
          <th style="width: 26%">{{ zh ? '类名' : 'Class' }}</th>
          <th style="width: 16%">{{ zh ? '包' : 'Package' }}</th>
          <th style="width: 42%">{{ zh ? '一句话职责' : 'Responsibility' }}</th>
          <th style="width: 16%">{{ zh ? '详解章节' : 'Chapter' }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="c in g.items" :key="c.name">
          <td data-label="Class"><span class="cname" v-html="hl(c.name)"></span></td>
          <td data-label="Package"><span class="cpkg">{{ c.pkg }}</span></td>
          <td data-label="Responsibility" v-html="hl(zh ? c.desc.zh : c.desc.en)"></td>
          <td data-label="Chapter">
            <a v-if="c.chapter" :href="siteHref(zh ? chapters[c.chapter].zh : chapters[c.chapter].en)">
              {{ zh ? chapterLabels[c.chapter].zh : chapterLabels[c.chapter].en }}
            </a>
            <span v-else style="color: var(--ds-text-3)">—</span>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>
