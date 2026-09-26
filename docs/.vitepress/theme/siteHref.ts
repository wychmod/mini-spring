/**
 * 站内链接助手。
 *
 * 背景：VitePress 只会自动处理 **markdown 语法** 写出来的站内链接
 * （补 base、未开启 cleanUrls 时补 .html）。而本项目大量使用原始 HTML
 * 卡片 / 组件来排版，这些 href 不会被处理，在 base = '/mini-spring/' 的
 * 部署下（GitHub Pages）直开或新标签页打开会 404。
 *
 * 因此统一走这里：
 *   - markdown 原始 HTML：<a :href="$href('/guide/aop')">
 *   - 组件内部：import { siteHref } from './siteHref'
 *
 * 输出的形式与 VitePress 自身的站内链接完全一致。
 */
import { withBase } from 'vitepress'

const state = {
  base: '/',
  cleanUrls: true,
}

/** 由 theme 的 enhanceApp 调用一次，注入站点配置 */
export function configureSiteHref(base: string, cleanUrls: boolean): void {
  state.base = base
  state.cleanUrls = cleanUrls
}

export function siteHref(path: string): string {
  const href = withBase(path)

  // 外链 / 锚点 / mailto：原样返回
  if (/^[a-z][a-z0-9+.-]*:/i.test(path) || path.startsWith('#') || path.startsWith('//')) {
    return path
  }

  // 开启了 cleanUrls 就保持无扩展名
  if (state.cleanUrls) return href

  // 不在站点 base 内（理论上不会发生）或已指向目录，保持原样
  if (!href.startsWith(state.base)) return href
  if (href.endsWith('/')) return href

  // 已经有扩展名（.html / .svg / .woff2 …）就不动
  if (/\.[a-z0-9]+$/i.test(href)) return href

  return `${href}.html`
}
