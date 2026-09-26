<script setup lang="ts">
/**
 * 目录结构导览：模块切换 + 包清单卡 + 包内类折叠清单
 * 数据直接复用类参考索引的 registry，保证两处永远一致。
 */
import { computed, ref } from 'vue'
import { useData } from 'vitepress'
import { siteHref } from './siteHref'
import Segmented from './Segmented.vue'
import { registry, pkgNotes, pkgOrder } from '../data/classes'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

const mod = ref<'iteration' | 'original'>('iteration')

const options = computed(() =>
  registry.length
    ? [
        {
          label: `mini-spring-iteration · ${registry.filter((c) => c.module === 'iteration').length}`,
          value: 'iteration',
        },
        {
          label: `mini-spring-original · ${registry.filter((c) => c.module === 'original').length}`,
          value: 'original',
        },
      ]
    : []
)

const pkgs = computed(() => {
  const items = registry.filter((c) => c.module === mod.value)
  const map = new Map<string, string[]>()
  for (const c of items) {
    if (!map.has(c.pkg)) map.set(c.pkg, [])
    map.get(c.pkg)!.push(c.name)
  }
  return [...map.entries()]
    .map(([pkg, names]) => ({ pkg, names: names.sort((a, b) => a.localeCompare(b)) }))
    .sort((a, b) => pkgOrder.indexOf(a.pkg) - pkgOrder.indexOf(b.pkg))
})

const open = ref<Record<string, boolean>>({})
function toggle(pkg: string) {
  open.value = { ...open.value, [pkg]: !open.value[pkg] }
}

const rootLabel = computed(() =>
  mod.value === 'iteration' ? 'springframework/' : 'framework/'
)
</script>

<template>
  <Segmented v-model="mod" :options="options" />

  <div class="ds-grid ds-grid--3" style="margin-top: 28px">
    <article v-for="p in pkgs" :key="p.pkg" class="ds-card ds-card--hover">
      <span class="tag">{{ rootLabel }}{{ p.pkg }}/</span>
      <h3>
        {{ p.names.length }}
        <span style="font-size: 13px; font-weight: 500; color: var(--ds-text-3)">
          {{ zh ? '个类' : 'classes' }}
        </span>
      </h3>
      <p>{{ zh ? pkgNotes[p.pkg]?.zh : pkgNotes[p.pkg]?.en }}</p>
      <button
        class="ds-card-link"
        type="button"
        style="background: none; border: none; padding: 0; cursor: pointer; font-family: inherit"
        :aria-expanded="!!open[p.pkg]"
        @click="toggle(p.pkg)"
      >
        {{ open[p.pkg] ? (zh ? '收起类清单' : 'Hide classes') : zh ? '展开类清单' : 'Show classes' }}
        <span class="chev" :style="{ transform: open[p.pkg] ? 'rotate(90deg)' : 'none' }">→</span>
      </button>
      <ul v-if="open[p.pkg]" class="ds-clslist" style="margin-top: 14px">
        <li v-for="n in p.names" :key="n">
          <code>{{ n }}</code>
        </li>
      </ul>
    </article>
  </div>

  <div class="ds-btn-row" style="margin-top: 28px">
    <a class="ds-btn ds-btn--ghost ds-btn--sm" :href="siteHref(zh ? '/reference' : '/en/reference')">
      {{ zh ? '在类参考索引中查看全部 127 个类 →' : 'See all 127 classes in the index →' }}
    </a>
  </div>
</template>
