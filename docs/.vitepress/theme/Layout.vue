<script setup lang="ts">
import DefaultTheme from 'vitepress/theme'
import { useData, useRoute } from 'vitepress'
import { computed } from 'vue'
import Ds404 from './Ds404.vue'

const { Layout: DefaultLayout } = DefaultTheme
const { frontmatter, lang } = useData()
const route = useRoute()

const isHome = computed(() => frontmatter.value.layout === 'home' || route.path === '/' || route.path === '/en/')

const skipLabel = computed(() => (lang.value.startsWith('zh') ? '跳到主内容' : 'Skip to content'))
</script>

<template>
  <DefaultLayout>
    <template #layout-top>
      <a class="ds-skip-link" href="#VPContent">{{ skipLabel }}</a>
    </template>

    <!--
      默认主题的 Layout 会把 #not-found 透传给 VPContent，
      VPContent 仅在 page.isNotFound 时渲染该插槽，
      且一旦插槽有内容就不再使用内置的 NotFound.vue。
      因此在这里挂载自研的 Ds404（双语 + 三个入口按钮）。
    -->
    <template #not-found>
      <Ds404 />
    </template>
  </DefaultLayout>
</template>
