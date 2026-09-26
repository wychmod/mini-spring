<script setup lang="ts">
/** 数据条：进入视口时计数动画（只播一次，减少动效偏好下直接显示终值） */
import { onMounted, onUnmounted, ref } from 'vue'

const props = withDefaults(
  defineProps<{
    items: { value: number; suffix?: string; label: string }[]
  }>(),
  { items: () => [] }
)

const el = ref<HTMLElement | null>(null)
const shown = ref<number[]>(props.items.map(() => 0))
const done = ref(false)
let io: IntersectionObserver | null = null

function play() {
  if (done.value) return
  done.value = true

  const reduce = window.matchMedia('(prefers-reduced-motion: reduce)').matches
  if (reduce) {
    shown.value = props.items.map((i) => i.value)
    return
  }

  const dur = 800
  const start = performance.now()
  const from = props.items.map(() => 0)

  function tick(now: number) {
    const p = Math.min(1, (now - start) / dur)
    // ease-out
    const e = 1 - Math.pow(1 - p, 3)
    shown.value = props.items.map((it, i) => Math.round(from[i] + (it.value - from[i]) * e))
    if (p < 1) requestAnimationFrame(tick)
  }
  requestAnimationFrame(tick)
}

onMounted(() => {
  if (!el.value) return
  io = new IntersectionObserver(
    (entries) => {
      if (entries.some((e) => e.isIntersecting)) {
        play()
        io?.disconnect()
      }
    },
    { threshold: 0.3 }
  )
  io.observe(el.value)
})

onUnmounted(() => io?.disconnect())
</script>

<template>
  <div ref="el" class="ds-stats">
    <div v-for="(it, i) in items" :key="i" class="ds-stat">
      <span class="num">{{ shown[i] }}{{ it.suffix || '' }}</span>
      <span class="label">{{ it.label }}</span>
    </div>
  </div>
</template>
