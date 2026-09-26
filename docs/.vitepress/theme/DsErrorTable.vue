<script setup lang="ts">
/**
 * 常见报错排查表：输入即过滤（现象 / 原因 / 解决 / 标签），移动端转置为卡片
 */
import { computed, ref } from 'vue'
import { useData } from 'vitepress'
import { errors } from '../data/errors'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

const q = ref('')

const filtered = computed(() => {
  const kw = q.value.trim().toLowerCase()
  if (!kw) return errors
  return errors.filter((e) =>
    [e.symptom, e.cause, e.fix, e.tag].some((f) =>
      (zh.value ? f.zh : f.en).toLowerCase().includes(kw)
    )
  )
})

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
</script>

<template>
  <div class="ds-refbar">
    <input
      type="search"
      :value="q"
      @input="q = ($event.target as HTMLInputElement).value"
      :placeholder="zh ? '输入报错关键字，例如 NoSuchMethod' : 'Type an error keyword, e.g. NoSuchMethod'"
      :aria-label="zh ? '搜索报错' : 'Search errors'"
    />
    <span class="count">{{ filtered.length }} / {{ errors.length }}</span>
  </div>

  <div v-if="filtered.length === 0" class="ds-refempty">
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.25">
      <circle cx="11" cy="11" r="7" />
      <path d="m20 20-3.5-3.5" />
    </svg>
    <p>{{ zh ? '没有匹配的报错' : 'No matching errors' }}</p>
    <button class="ds-btn ds-btn--ghost ds-btn--sm" type="button" @click="q = ''">
      {{ zh ? '清除筛选' : 'Clear' }}
    </button>
  </div>

  <table v-else class="ds-reftable">
    <thead>
      <tr>
        <th style="width: 27%">{{ zh ? '现象' : 'Symptom' }}</th>
        <th style="width: 15%">{{ zh ? '标签' : 'Tag' }}</th>
        <th style="width: 29%">{{ zh ? '原因' : 'Cause' }}</th>
        <th style="width: 29%">{{ zh ? '解决' : 'Fix' }}</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(e, i) in filtered" :key="i">
        <td data-label="Symptom"><span class="cname" v-html="hl(e.symptom.zh ? (zh ? e.symptom.zh : e.symptom.en) : e.symptom.en)"></span></td>
        <td data-label="Tag">
          <span class="ds-badge ds-badge--plain">{{ zh ? e.tag.zh : e.tag.en }}</span>
        </td>
        <td data-label="Cause" v-html="hl(zh ? e.cause.zh : e.cause.en)"></td>
        <td data-label="Fix" v-html="hl(zh ? e.fix.zh : e.fix.en)"></td>
      </tr>
    </tbody>
  </table>
</template>
