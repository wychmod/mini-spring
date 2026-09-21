import { defineConfig } from 'vitepress'

export default defineConfig({
  title: 'mini-spring',
  head: [['link', { rel: 'icon', type: 'image/svg+xml', href: '/mini-spring/favicon.svg' }]],
  base: '/mini-spring/',

  locales: {
    root: {
      label: '简体中文',
      lang: 'zh-CN',
      description:
        '从 0 开始手写一个 Spring 框架 —— 用两条实现路径讲透 IoC、DI、AOP、事件与三级缓存',
      themeConfig: {
        nav: [
          { text: '文档', link: '/guide/preface' },
          { text: 'IoC 全景类图', link: '/guide/ioc/class-diagram' },
          { text: 'GitHub', link: 'https://github.com/wychmod/mini-spring' },
        ],
        sidebar: [
          {
            text: '前言',
            items: [
              { text: '为什么手写 Spring', link: '/guide/preface' },
              { text: '项目总览：两条实现路径', link: '/guide/overview' },
            ],
          },
          {
            text: '快速开始',
            items: [{ text: '环境与运行', link: '/guide/getting-started' }],
          },
          {
            text: 'IoC 容器',
            items: [
              { text: '容器启动全流程', link: '/guide/ioc/bootstrap' },
              { text: 'Bean 生命周期', link: '/guide/ioc/lifecycle' },
              { text: '三级缓存解决循环依赖', link: '/guide/ioc/circular-deps' },
              { text: 'IoC 全景类图', link: '/guide/ioc/class-diagram' },
            ],
          },
          {
            text: '进阶特性',
            items: [
              { text: 'AOP 原理与实现', link: '/guide/aop' },
              { text: '事件监听与发布', link: '/guide/advanced/events' },
            ],
          },
        ],
        outline: { level: [2, 3], label: '本页目录' },
        docFooter: { prev: '上一篇', next: '下一篇' },
        lastUpdated: { text: '最后更新' },
        returnToTopLabel: '回到顶部',
        sidebarMenuLabel: '目录',
        darkModeSwitchLabel: '外观',
        lightModeSwitchTitle: '切换到亮色模式',
        darkModeSwitchTitle: '切换到暗色模式',
        search: {
          options: {
            translations: {
              button: { buttonText: '搜索文档', buttonAriaLabel: '搜索文档' },
              modal: {
                noResultsText: '未找到相关结果',
                resetButtonTitle: '清除查询条件',
                displayDetails: '显示详细列表',
                footer: { selectText: '选择', navigateText: '切换', closeText: '关闭' },
              },
            },
          },
        },
        footer: {
          copyright: 'Released under the Apache License 2.0 · QQ 群 545480453',
        },
      },
    },
    en: {
      label: 'English',
      lang: 'en-US',
      link: '/en/',
      description:
        'Build your own Spring from scratch — two implementation paths covering IoC, DI, AOP, events and the three-level cache',
      themeConfig: {
        nav: [
          { text: 'Docs', link: '/en/guide/preface' },
          { text: 'IoC Class Diagram', link: '/en/guide/ioc/class-diagram' },
          { text: 'GitHub', link: 'https://github.com/wychmod/mini-spring' },
        ],
        sidebar: [
          {
            text: 'Introduction',
            items: [
              { text: 'Why Build Your Own Spring', link: '/en/guide/preface' },
              { text: 'Overview: Two Paths', link: '/en/guide/overview' },
            ],
          },
          {
            text: 'Getting Started',
            items: [{ text: 'Setup & Run', link: '/en/guide/getting-started' }],
          },
          {
            text: 'IoC Container',
            items: [
              { text: 'Container Bootstrap Flow', link: '/en/guide/ioc/bootstrap' },
              { text: 'Bean Lifecycle', link: '/en/guide/ioc/lifecycle' },
              { text: 'Three-Level Cache & Circular Dependencies', link: '/en/guide/ioc/circular-deps' },
              { text: 'IoC Class Diagram', link: '/en/guide/ioc/class-diagram' },
            ],
          },
          {
            text: 'Advanced',
            items: [
              { text: 'AOP', link: '/en/guide/aop' },
              { text: 'Events', link: '/en/guide/advanced/events' },
            ],
          },
        ],
        outline: { level: [2, 3], label: 'On this page' },
        returnToTopLabel: 'Back to top',
        sidebarMenuLabel: 'Menu',
        darkModeSwitchLabel: 'Appearance',
        lightModeSwitchTitle: 'Switch to light theme',
        darkModeSwitchTitle: 'Switch to dark theme',
        footer: {
          copyright: 'Released under the Apache License 2.0 · QQ Group 545480453',
        },
      },
    },
  },

  themeConfig: {
    logo: '/images/logo.svg',
    siteTitle: 'mini-spring',
    socialLinks: [{ icon: 'github', link: 'https://github.com/wychmod/mini-spring' }],
    search: { provider: 'local' },
  },
})
