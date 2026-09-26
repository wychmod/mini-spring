/**
 * 构建前守卫：检测 markdown 中 <section> 顶层 HTML 块内部的空行。
 *
 * 为什么需要它：
 * markdown-it 的 HTML block(type 6) 在遇到空行时即终止，其后的缩进标签会被
 * 当作「缩进代码块」渲染成 <pre><code>，页面会出现代码泄漏、栅格失效。
 * 由于门户/分流层页面把所有区块都包在 <section> 里，块内不得出现空行。
 *
 * 该脚本在 `npm run build` 前自动执行（prebuild），发现违规则构建失败并列出位置。
 */
import { readFileSync, readdirSync, statSync } from 'node:fs'
import { join, relative } from 'node:path'
import { fileURLToPath } from 'node:url'

const ROOT = fileURLToPath(new URL('..', import.meta.url))

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

const problems = []

for (const file of walk(ROOT)) {
  const lines = readFileSync(file, 'utf8').split(/\r?\n/)
  let depth = 0
  let startLine = 0
  const blanks = []
  lines.forEach((line, i) => {
    if (/^<section\b/.test(line)) {
      depth += 1
      if (depth === 1) startLine = i + 1
    } else if (/^<\/section>/.test(line) && depth > 0) {
      depth -= 1
    } else if (depth > 0 && line.trim() === '') {
      blanks.push(i + 1)
    }
  })
  if (blanks.length) {
    problems.push({ file: relative(ROOT, file), startLine, blanks })
  }
}

if (problems.length) {
  console.error('\n[check-html-blocks] HTML 块内出现空行，会导致渲染为代码块：\n')
  for (const p of problems) {
    console.error(`  ${p.file}  <section> 起始于第 ${p.startLine} 行，块内空行：${p.blanks.join(', ')}`)
  }
  console.error('\n修复方式：删除 <section> … </section> 之间的空行（块内全部是原始 HTML）。\n')
  process.exit(1)
}

console.log('[check-html-blocks] OK — 未发现 HTML 块内空行')
