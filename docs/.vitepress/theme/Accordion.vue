<script setup lang="ts">
/**
 * 折叠面板（FAQ / 报错排查 / 目录导览通用）
 * props.items: [{ q, a }]   a 支持 HTML 字符串
 */
import { ref } from 'vue'

const props = withDefaults(
  defineProps<{
    items: { q: string; a: string; open?: boolean }[]
  }>(),
  { items: () => [] }
)

const open = ref<Record<number, boolean>>(
  Object.fromEntries(props.items.map((it, i) => [i, !!it.open]))
)

function toggle(i: number) {
  open.value = { ...open.value, [i]: !open.value[i] }
}
</script>

<template>
  <div class="ds-accordion">
    <div v-for="(it, i) in items" :key="i" class="ds-acc-item">
      <button class="ds-acc-trigger" type="button" :aria-expanded="!!open[i]" @click="toggle(i)">
        <span>{{ it.q }}</span>
        <svg class="chev" viewBox="0 0 16 16" fill="none" stroke="currentColor" stroke-width="1.5">
          <path d="m6 3.5 5 4.5-5 4.5" />
        </svg>
      </button>
      <div v-show="open[i]" class="ds-acc-panel" v-html="it.a"></div>
    </div>
  </div>
</template>
