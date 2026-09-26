"""移除 markdown 中 <section> 顶层 HTML 块内部的空行。

markdown-it 的 HTML block(type 6) 会在遇到空行时终止，之后的缩进标签会被
当作缩进代码块渲染成 <pre><code>。由于这些 <section> 块内全部是原始 HTML，
安全地删除内部空行即可让整块被原样透传。
"""
import re
from pathlib import Path

ROOT = Path('D:/idea/mini-spring/docs')
FILES = [
    'index.md', 'en/index.md',
    'compare.md', 'en/compare.md',
    'reference.md', 'en/reference.md',
    'roadmap.md', 'en/roadmap.md',
]

for rel in FILES:
    p = ROOT / rel
    lines = p.read_text(encoding='utf-8').splitlines()
    out = []
    depth = 0
    removed = 0
    for ln in lines:
        if re.match(r'^<section\b', ln):
            depth += 1
            out.append(ln)
            continue
        if re.match(r'^</section>', ln) and depth:
            depth -= 1
            out.append(ln)
            continue
        if depth >= 1 and ln.strip() == '':
            removed += 1
            continue
        out.append(ln)
    p.write_text('\n'.join(out) + '\n', encoding='utf-8')
    print(f'{rel}: 移除 {removed} 处块内空行')
