/**
 * 构建前守卫：检测 markdown 原始 HTML 里「没有走站点助手」的站内链接。
 *
 * 为什么需要它：
 * VitePress 只会自动处理 **markdown 语法** 写出来的站内链接（补 base、
 * 未开启 cleanUrls 时补 .html）。本项目大量使用原始 HTML 来排版卡片和区块，
 * 其中的 href="/xxx" / src="/xxx" 不会被处理，在 base = '/mini-spring/' 的
 * 部署（GitHub Pages）下直开或新标签页打开会 404。
 *
 * 正确写法：:href="$href('/guide/aop')"  （组件内用 siteHref()）
 *
 * 该脚本在 `npm run build` 前自动执行（prebuild），发现违规则构建失败。
 */
import { readFileSync, readdirSync, statSync } from 'node:fs'
import { join, relative } from 'node:path'
import { fileURLToPath } from 'node:url'

const ROOT = fileURLToPath(new URL('..', import.meta.url))
const BASE = '/mini-spring'

function walk(dir) {
  const out = []
  for (const name of readdirSync(dir)) {
    if (name === 'node_modules' || name === '.vitepress' || name === 'dist') continue
    const full = join(dir, name)
    const st = statSync(full)
    if (st.isDirectory()) out.push(...walk(full))
    else if (name.endsWith('.md')) out.push(full)
  }
  return out
}

const RE = /\b(href|src)="(\/[^"]*)"/g
const problems = []

for (const file of walk(ROOT)) {
  const lines = readFileSync(file, 'utf8').split(/\r?\n/)
  lines.forEach((line, i) => {
    for (const m of line.matchAll(RE)) {
      const [, attr, value] = m
      if (value.startsWith('//')) continue // 协议相对，属于外链
      if (value.startsWith(BASE)) continue // 已带 base
      problems.push({ file: relative(ROOT, file), line: i + 1, attr, value })
    }
  })
}

if (problems.length) {
  console.error('\n[check-internal-links] 发现未走站点助手的站内链接（部署后会 404）：\n')
  for (const p of problems) {
    console.error(`  ${p.file}:${p.line}  ${p.attr}="${p.value}"`)
  }
  console.error('\n修复方式：改用 :href="$href(\'…\')"（组件内用 siteHref()），')
  console.error('它会补 base，并在未开启 cleanUrls 时补 .html。\n')
  process.exit(1)
}

console.log('[check-internal-links] OK — 站内链接均已走站点助手')
