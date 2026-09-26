import DefaultTheme from 'vitepress/theme'
import type { Theme } from 'vitepress'
import { configureSiteHref, siteHref } from './siteHref'
import Layout from './Layout.vue'

import Hero from './Hero.vue'
import StatBar from './StatBar.vue'
import FigureGallery from './FigureGallery.vue'
import ClassIndex from './ClassIndex.vue'
import Roadmap from './Roadmap.vue'
import Accordion from './Accordion.vue'
import Segmented from './Segmented.vue'
import Compare from './Compare.vue'
import CodeGlance from './CodeGlance.vue'
import DsDocHead from './DsDocHead.vue'
import DsStructure from './DsStructure.vue'
import DsErrorTable from './DsErrorTable.vue'
import DsFaq from './DsFaq.vue'
import Ds404 from './Ds404.vue'

import './fonts.css'
import './tokens.css'
import './base.css'
import './components.css'

export default {
  extends: DefaultTheme,
  Layout,
  enhanceApp({ app, siteData }) {
    /**
     * 供 markdown 里手写的原始 HTML 使用：<a :href="$href('/guide/aop')">
     *
     * 原始 HTML 中的 href="/xxx" 不会被 VitePress 自动处理，在带 base 的部署
     * （GitHub Pages /mini-spring/）下直开或新标签页打开会 404。
     * 具体规则见 ./siteHref.ts。
     */
    configureSiteHref(siteData.value.base, !!siteData.value.cleanUrls)
    app.config.globalProperties.$href = siteHref

    app.component('DsHero', Hero)
    app.component('DsStatBar', StatBar)
    app.component('DsFigureGallery', FigureGallery)
    app.component('DsClassIndex', ClassIndex)
    app.component('DsRoadmap', Roadmap)
    app.component('DsAccordion', Accordion)
    app.component('DsSegmented', Segmented)
    app.component('DsCompare', Compare)
    app.component('DsCodeGlance', CodeGlance)
    app.component('DsDocHead', DsDocHead)
    app.component('DsStructure', DsStructure)
    app.component('DsErrorTable', DsErrorTable)
    app.component('DsFaq', DsFaq)
    app.component('Ds404', Ds404)
  },
} satisfies Theme
