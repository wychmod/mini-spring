<script setup lang="ts">
/**
 * 学习路线：三档位切换 + 时间轴
 * 档位：quick（速览 1 小时）· standard（标准 1 周）· deep（精读 3 周）
 */
import { computed, ref } from 'vue'
import { useData } from 'vitepress'
import { siteHref } from './siteHref'
import Segmented from './Segmented.vue'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

interface Node {
  title: string
  time: string
  out: string
  accept: string
  href?: string
}

const data: Record<string, Node[]> = {
  quick: [
    { title: '读图 + 结论', time: '15 分钟', out: '看懂 5 张架构图', accept: '能说出 Bean 的大致流转', href: '/guide/ioc/class-diagram' },
    { title: '扫一遍启动流程', time: '20 分钟', out: '知道 refresh() 九步是什么', accept: '能复述九步顺序', href: '/guide/ioc/bootstrap' },
    { title: '只看三级缓存', time: '25 分钟', out: '理解为何要三个缓存', accept: '能讲清第三级存工厂的原因', href: '/guide/ioc/circular-deps' },
  ],
  standard: [
    { title: '读前言', time: '30 分钟', out: '选定实现路径', accept: '能说出两条路径的差异', href: '/guide/preface' },
    { title: '跑通 Demo', time: '1 小时', out: 'mvn test 全绿', accept: '看过 CircleTest 的输出', href: '/guide/getting-started' },
    { title: '容器启动全流程', time: '1.5 小时', out: '画出 refresh 九步', accept: '能复述调用链', href: '/guide/ioc/bootstrap' },
    { title: 'Bean 生命周期', time: '2 小时', out: '标注主流程图', accept: '说清三条属性填充路径', href: '/guide/ioc/lifecycle' },
    { title: '三级缓存', time: '2 小时', out: '复现循环依赖', accept: '讲清为何是三级不是两级', href: '/guide/ioc/circular-deps' },
    { title: 'AOP 与事件', time: '2 小时', out: '自己配一个切面', accept: '说出代理织入时机', href: '/guide/aop' },
  ],
  deep: [
    { title: '读前言与架构总览', time: '1 小时', out: '选定路径并规划进度', accept: '画出两模块的包对照', href: '/guide/overview' },
    { title: '跑通 Demo 并读测试', time: '2 小时', out: '读懂 ApiTest 与 CircleTest', accept: '能补一个新的测试用例', href: '/guide/getting-started' },
    { title: '目录结构导览', time: '1 小时', out: '摸清 6 个包的职责', accept: '能说出任意类属于哪个包', href: '/guide/start/structure' },
    { title: '容器启动全流程', time: '3 小时', out: '逐行对照 refresh() 源码', accept: '指出九步各自对应的类', href: '/guide/ioc/bootstrap' },
    { title: 'Bean 生命周期', time: '4 小时', out: '对照 Spring 源码逐段比对', accept: '找出 mini 版省略的扩展点', href: '/guide/ioc/lifecycle' },
    { title: '三级缓存', time: '4 小时', out: '手写一遍缓存降级逻辑', accept: '讲清为何二级缓存不能省', href: '/guide/ioc/circular-deps' },
    { title: 'AOP 原理', time: '4 小时', out: '实现一个自定义 Advisor', accept: '对比 JDK 与 CGLIB 的边界', href: '/guide/aop' },
    { title: '事件机制', time: '2 小时', out: '自定义事件与监听器', accept: '说明广播器的工作方式', href: '/guide/advanced/events' },
    { title: '精读类图', time: '3 小时', out: '按继承树整理一遍类关系', accept: '能徒手画出 BeanFactory 树', href: '/guide/ioc/class-diagram' },
  ],
}

const mode = ref<'quick' | 'standard' | 'deep'>('standard')

const options = computed(() =>
  zh.value
    ? [
        { label: '速览版 · 1 小时', value: 'quick' },
        { label: '标准版 · 1 周', value: 'standard' },
        { label: '精读版 · 3 周', value: 'deep' },
      ]
    : [
        { label: 'Quick · 1 hour', value: 'quick' },
        { label: 'Standard · 1 week', value: 'standard' },
        { label: 'Deep · 3 weeks', value: 'deep' },
      ]
)

const nodes = computed(() => data[mode.value])

const summary = computed(() => {
  if (!zh.value) {
    return {
      quick: 'Read the diagrams and conclusions only — enough to hold a conversation.',
      standard: 'Read the docs and run the tests — enough to explain the design.',
      deep: 'Compare against real Spring source line by base — enough to rebuild it.',
    }[mode.value]
  }
  return {
    quick: '只读图和结论，够你在面试里讲清概念。',
    standard: '读文档 + 跑测试，够你向别人讲清设计取舍。',
    deep: '逐行对照真实 Spring 源码，够你徒手复刻一遍。',
  }[mode.value]
})

const pathFor = (href?: string) => {
  if (!href) return undefined
  // href 写的是站内绝对路径（不带 base），统一交给 siteHref 处理
  return siteHref(zh.value ? href : `/en${href}`)
}
</script>

<template>
  <Segmented v-model="mode" :options="options" />

  <p class="ds-sub" style="margin: 16px 0 28px">{{ summary }}</p>

  <div class="ds-timeline">
    <div
      v-for="(n, i) in nodes"
      :key="i"
      class="ds-tl-item"
      :class="{ 'ds-tl-item--todo': i > 1 && mode === 'standard' }"
    >
      <div class="ds-tl-card">
        <div class="ds-tl-head">
          <span class="title">{{ i + 1 }}. {{ n.title }}</span>
          <span class="time">{{ n.time }}</span>
        </div>
        <div class="ds-tl-meta">
          <span><span class="k">{{ zh ? '产出' : 'Output' }}</span>{{ n.out }}</span>
          <span><span class="k">{{ zh ? '验收' : 'Check' }}</span>{{ n.accept }}</span>
        </div>
        <a
          v-if="n.href"
          class="ds-card-link"
          style="margin-top: 12px; padding-top: 10px"
          :href="pathFor(n.href)"
        >
          {{ zh ? '进入章节' : 'Open chapter' }} →
        </a>
      </div>
    </div>
  </div>
</template>
