<script setup lang="ts">
/** 两条路径对照表：桌面并排、移动端转置为逐项卡片 */
import { computed } from 'vue'
import { withBase, useData } from 'vitepress'

const { lang } = useData()
const zh = computed(() => lang.value.startsWith('zh'))

const rows = computed(() =>
  zh.value
    ? [
        ['定位', '30 个类的极简版', '源码风格的渐进式实现'],
        ['类数量', '29 个框架类', '98 个框架类'],
        ['类命名', '独立命名，链路极短', '与 Spring 源码同名同分层'],
        ['包组织', '4 个功能包', '6 个分层包'],
        ['配置方式', '注解扫描', 'XML + 注解扫描'],
        ['容器入口', 'ApplicationContext 扫描注解', 'ClassPathXmlApplicationContext + refresh()'],
        ['MVC 支持', '有：DispatchServlet + HandlerMapping', '无'],
        ['AOP 实现', 'JDK / CGLIB 双代理 + 方法拦截链', 'JDK / CGLIB 双代理 + Advisor 自动织入'],
        ['循环依赖', '一级缓存演示（CircleTest）', '完整三级缓存实现'],
        ['事件机制', '无', '广播器 + 生命周期事件'],
        ['类型转换', '无', 'ConversionService 完整体系'],
        ['扩展点', '无', 'BeanFactoryPostProcessor / BeanPostProcessor'],
        ['注释密度', '高', '高，且与 Spring 源码逐段对照'],
        ['配套书籍', '《Spring 5 核心原理与 30 个类手写实战》', '《手写 Spring：渐进式源码实践》'],
        ['适合谁', '第一次接触，建立直觉', '读过一遍后，深入每个细节'],
        ['建议用时', '约 1 周', '约 3 周'],
      ]
    : [
        ['Focus', 'A 30-class minimal version', 'Progressive, source-code-style'],
        ['Class count', '29 framework classes', '98 framework classes'],
        ['Naming', 'Standalone names, very short chains', 'Same names and layering as Spring'],
        ['Packages', '4 functional packages', '6 layered packages'],
        ['Configuration', 'Annotation scanning', 'XML + annotation scanning'],
        ['Container entry', 'ApplicationContext scanning annotations', 'ClassPathXmlApplicationContext + refresh()'],
        ['MVC support', 'Yes: DispatchServlet + HandlerMapping', 'No'],
        ['AOP', 'JDK / CGLIB proxies + interception chain', 'JDK / CGLIB proxies + Advisor auto-weaving'],
        ['Circular deps', 'Single-level cache demo (CircleTest)', 'Full three-level cache'],
        ['Events', 'No', 'Multicaster + lifecycle events'],
        ['Type conversion', 'No', 'Full ConversionService system'],
        ['Extension points', 'No', 'BeanFactoryPostProcessor / BeanPostProcessor'],
        ['Comments', 'Dense', 'Dense, cross-checked with Spring source'],
        ['Companion book', '"Spring 5 Core Principles"', '"Handwriting Spring: Progressive Source Practice"'],
        ['Best for', 'First contact — build intuition', 'After one pass — dig into details'],
        ['Suggested time', 'About 1 week', 'About 3 weeks'],
      ]
)

const head = computed(() =>
  zh.value ? ['维度', 'mini-spring-original', 'mini-spring-iteration'] : ['Dimension', 'mini-spring-original', 'mini-spring-iteration']
)
</script>

<template>
  <div class="ds-compare">
    <table class="ds-compare-table">
      <thead>
        <tr>
          <th v-for="(h, i) in head" :key="i" :style="i === 0 ? { width: '24%' } : { width: '38%' }">
            {{ h }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(r, i) in rows" :key="i">
          <td data-label="Dimension">{{ r[0] }}</td>
          <td data-label="original">{{ r[1] }}</td>
          <td data-label="iteration">{{ r[2] }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
