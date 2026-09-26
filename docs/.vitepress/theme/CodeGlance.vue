<script setup lang="ts">
/**
 * 代码一瞥：源码 + 要点解读双栏
 * 代码片段内置在组件中，避免在 markdown 里手写 HTML 转义（markdown 会解码实体导致 Vue 解析失败）
 */
import { computed } from 'vue'
import { useData } from 'vitepress'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

/** 极简 Java 着色：注释 / 关键字 / 类型 */
const KEYWORDS = new Set([
  'private', 'protected', 'public', 'final', 'static', 'class', 'interface', 'new', 'return',
  'void', 'if', 'else', 'for', 'while', 'implements', 'extends', 'null', 'true', 'false',
])

interface Line {
  text: string
  kind?: 'comment' | 'code'
}

const snippets: Record<string, { file: string; lines: Line[] }> = {
  zh: {
    file: 'DefaultSingletonBeanRegistry.java',
    lines: [
      { text: '/** 一级缓存，普通对象 */', kind: 'comment' },
      { text: 'private Map<String, Object> singletonObjects' },
      { text: '        = new HashMap<>();' },
      { text: '' },
      { text: '/** 二级缓存，提前暴露对象 */', kind: 'comment' },
      { text: 'protected final Map<String, Object> earlySingletonObjects' },
      { text: '        = new HashMap<>();' },
      { text: '' },
      { text: '/** 三级缓存，存放代理对象工厂 */', kind: 'comment' },
      { text: 'private final Map<String, ObjectFactory<?>>' },
      { text: '        singletonFactories = new HashMap<>();' },
    ],
  },
  en: {
    file: 'DefaultSingletonBeanRegistry.java',
    lines: [
      { text: '/** Level 1: fully initialized singletons */', kind: 'comment' },
      { text: 'private Map<String, Object> singletonObjects' },
      { text: '        = new HashMap<>();' },
      { text: '' },
      { text: '/** Level 2: early references, exposed on demand */', kind: 'comment' },
      { text: 'protected final Map<String, Object> earlySingletonObjects' },
      { text: '        = new HashMap<>();' },
      { text: '' },
      { text: '/** Level 3: factories producing early proxies */', kind: 'comment' },
      { text: 'private final Map<String, ObjectFactory<?>>' },
      { text: '        singletonFactories = new HashMap<>();' },
    ],
  },
}

const cur = computed(() => snippets[zh.value ? 'zh' : 'en'])

const levelClass = ['c1', 'c2', 'c1']

/** 把一行代码拆成着色片段 */
function tokens(line: string) {
  if (!line) return []
  if (line.trimStart().startsWith('/**') || line.trimStart().startsWith('*')) {
    return [{ t: line, c: 'cm' }]
  }
  const out: { t: string; c: string }[] = []
  const re = /[A-Za-z_$][\w$]*/g
  let last = 0
  let m: RegExpExecArray | null
  while ((m = re.exec(line))) {
    if (m.index > last) out.push({ t: line.slice(last, m.index), c: '' })
    const w = m[0]
    let c = ''
    if (KEYWORDS.has(w)) c = 'c1'
    else if (/^(Map|HashMap|Object|ObjectFactory|String|List|Set|ObjectFactoryRegistry)$/.test(w)) c = 'c2'
    out.push({ t: w, c })
    last = m.index + w.length
  }
  if (last < line.length) out.push({ t: line.slice(last), c: '' })
  return out
}

const points = computed(() =>
  zh.value
    ? [
        {
          t: '三级存的是工厂，不是对象',
          d: '这样只有在真正发生循环依赖时，才会调用工厂去提前生成代理 —— 避免了无谓的代理创建。',
        },
        {
          t: '命中后立即降级缓存',
          d: '三级命中后把结果放入二级并移除三级，保证同一个 Bean 只会被生成一次，全局唯一。',
        },
        {
          t: '为什么不能只有两级',
          d: '如果二级直接存对象，普通 Bean 也会被迫提前暴露，代理的创建时机就被迫提前了。',
        },
      ]
    : [
        {
          t: 'Level 3 stores factories, not objects',
          d: 'A proxy is only produced when a circular dependency genuinely occurs — no wasted proxy creation.',
        },
        {
          t: 'A hit immediately demotes the cache',
          d: 'On a level-3 hit the result moves into level 2 and is removed from level 3, guaranteeing one instance per bean.',
        },
        {
          t: 'Why two levels are not enough',
          d: 'If level 2 held objects, ordinary beans would be forced into early exposure and proxies would be created too soon.',
        },
      ]
)
</script>

<template>
  <div class="ds-glance">
    <div class="ds-glance-code">
      <div class="bar">
        <span>{{ cur.file }}</span>
      </div>
      <pre><code><template v-for="(l, i) in cur.lines" :key="i"><span v-if="!l.text"></span><template v-else><span
              v-for="(tk, j) in tokens(l.text)"
              :key="j"
              :class="tk.c"
            >{{ tk.t }}</span></template>
</template></code></pre>
    </div>

    <ul class="ds-points">
      <li v-for="(p, i) in points" :key="i">
        <b>{{ p.t }}</b>
        {{ p.d }}
      </li>
    </ul>
  </div>
</template>

<style scoped>
.ds-glance-code pre {
  white-space: pre;
}
</style>
