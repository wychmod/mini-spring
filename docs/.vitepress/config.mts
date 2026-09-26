import { defineConfig } from 'vitepress'

export default defineConfig({
  title: 'mini-spring',
  head: [
    ['link', { rel: 'icon', type: 'image/svg+xml', href: '/mini-spring/favicon.svg' }],
    ['meta', { name: 'theme-color', content: '#2e63e7' }],
  ],
  base: '/mini-spring/',

  markdown: {
    theme: { light: 'github-dark', dark: 'github-dark' },
  },

  locales: {
    root: {
      label: '简体中文',
      lang: 'zh-CN',
      description:
        '从 0 开始手写一个 Spring 框架 —— 用两条实现路径讲透 IoC、DI、AOP、事件与三级缓存',
      themeConfig: {
        nav: [
          {
            text: '文档',
            items: [
              {
                text: '快速开始',
                items: [
                  { text: '环境与运行', link: '/guide/getting-started' },
                  { text: '目录结构导览', link: '/guide/start/structure' },
                  { text: '常见报错排查', link: '/guide/start/troubleshooting' },
                ],
              },
              {
                text: '架构',
                items: [
                  { text: '项目总览：两条实现路径', link: '/guide/overview' },
                  { text: 'IoC 全景类图', link: '/guide/ioc/class-diagram' },
                ],
              },
              {
                text: '核心原理',
                items: [
                  { text: '容器启动全流程', link: '/guide/ioc/bootstrap' },
                  { text: 'Bean 生命周期', link: '/guide/ioc/lifecycle' },
                  { text: '三级缓存解决循环依赖', link: '/guide/ioc/circular-deps' },
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
          },
          { text: '对比', link: '/compare' },
          { text: '类参考', link: '/reference' },
          { text: '路线', link: '/roadmap' },
          {
            text: '更多',
            items: [
              { text: '常见问题 FAQ', link: '/faq' },
              { text: '更新日志', link: '/changelog' },
              { text: '关于与致谢', link: '/about' },
              { text: '贡献指南', link: '/contributing' },
            ],
          },
        ],
        sidebar: {
          '/guide/': [
            {
              text: '开始',
              items: [
                { text: '为什么手写 Spring', link: '/guide/preface' },
                { text: '项目总览：两条实现路径', link: '/guide/overview' },
              ],
            },
            {
              text: '快速开始',
              items: [
                { text: '环境与运行', link: '/guide/getting-started' },
                { text: '目录结构导览', link: '/guide/start/structure' },
                { text: '常见报错排查', link: '/guide/start/troubleshooting' },
              ],
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
            {
              text: '参考',
              items: [
                { text: '两条路径对比', link: '/compare' },
                { text: '类参考索引', link: '/reference' },
                { text: '学习路线', link: '/roadmap' },
              ],
            },
            {
              text: '项目',
              items: [
                { text: '常见问题 FAQ', link: '/faq' },
                { text: '更新日志', link: '/changelog' },
                { text: '关于与致谢', link: '/about' },
                { text: '贡献指南', link: '/contributing' },
              ],
            },
          ],
        },
        outline: { level: [2, 3], label: '本页目录' },
        docFooter: { prev: '上一篇', next: '下一篇' },
        lastUpdated: { text: '最后更新' },
        returnToTopLabel: '回到顶部',
        sidebarMenuLabel: '目录',
        darkModeSwitchLabel: '外观',
        lightModeSwitchTitle: '切换到亮色模式',
        darkModeSwitchTitle: '切换到暗色模式',
        langMenuLabel: '切换语言',
        externalLinkIcon: true,
        notFound: {
          title: '页面走丢了',
          quote: '它可能已被移动或删除，试试下面的入口。',
          linkLabel: '返回首页',
          linkText: '回到首页',
        },
        search: {
          options: {
            translations: {
              button: { buttonText: '搜索文档', buttonAriaLabel: '搜索文档' },
              modal: {
                displayDetails: '显示详细列表',
                noResultsText: '未找到相关结果',
                resetButtonTitle: '清除查询条件',
                footer: { selectText: '选择', navigateText: '切换', closeText: '关闭' },
              },
            },
          },
        },
        footer: {
          copyright: 'Released under the Apache License 2.0 · © 2026 wychmod',
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
          {
            text: 'Docs',
            items: [
              {
                text: 'Getting Started',
                items: [
                  { text: 'Setup & Run', link: '/en/guide/getting-started' },
                  { text: 'Project Structure', link: '/en/guide/start/structure' },
                  { text: 'Troubleshooting', link: '/en/guide/start/troubleshooting' },
                ],
              },
              {
                text: 'Architecture',
                items: [
                  { text: 'Overview: Two Paths', link: '/en/guide/overview' },
                  { text: 'IoC Class Diagram', link: '/en/guide/ioc/class-diagram' },
                ],
              },
              {
                text: 'Core',
                items: [
                  { text: 'Container Bootstrap Flow', link: '/en/guide/ioc/bootstrap' },
                  { text: 'Bean Lifecycle', link: '/en/guide/ioc/lifecycle' },
                  { text: 'Three-Level Cache', link: '/en/guide/ioc/circular-deps' },
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
          },
          { text: 'Compare', link: '/en/compare' },
          { text: 'Reference', link: '/en/reference' },
          { text: 'Roadmap', link: '/en/roadmap' },
          {
            text: 'More',
            items: [
              { text: 'FAQ', link: '/en/faq' },
              { text: 'Changelog', link: '/en/changelog' },
              { text: 'About', link: '/en/about' },
              { text: 'Contributing', link: '/en/contributing' },
            ],
          },
        ],
        sidebar: {
          '/en/guide/': [
            {
              text: 'Start',
              items: [
                { text: 'Why Build Your Own Spring', link: '/en/guide/preface' },
                { text: 'Overview: Two Paths', link: '/en/guide/overview' },
              ],
            },
            {
              text: 'Getting Started',
              items: [
                { text: 'Setup & Run', link: '/en/guide/getting-started' },
                { text: 'Project Structure', link: '/en/guide/start/structure' },
                { text: 'Troubleshooting', link: '/en/guide/start/troubleshooting' },
              ],
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
            {
              text: 'Reference',
              items: [
                { text: 'Two Paths Compared', link: '/en/compare' },
                { text: 'Class Index', link: '/en/reference' },
                { text: 'Learning Roadmap', link: '/en/roadmap' },
              ],
            },
            {
              text: 'Project',
              items: [
                { text: 'FAQ', link: '/en/faq' },
                { text: 'Changelog', link: '/en/changelog' },
                { text: 'About', link: '/en/about' },
                { text: 'Contributing', link: '/en/contributing' },
              ],
            },
          ],
        },
        outline: { level: [2, 3], label: 'On this page' },
        docFooter: { prev: 'Previous', next: 'Next' },
        lastUpdated: { text: 'Last updated' },
        returnToTopLabel: 'Back to top',
        sidebarMenuLabel: 'Menu',
        darkModeSwitchLabel: 'Appearance',
        lightModeSwitchTitle: 'Switch to light theme',
        darkModeSwitchTitle: 'Switch to dark theme',
        langMenuLabel: 'Change language',
        notFound: {
          title: 'Page not found',
          quote: 'It may have been moved or removed. Try one of these instead.',
          linkLabel: 'Go home',
          linkText: 'Back to home',
        },
        footer: {
          copyright: 'Released under the Apache License 2.0 · © 2026 wychmod',
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
