"""检测 markdown 中 HTML 块内部的空行 —— 空行会终止 markdown-it 的 HTML 块解析，
导致其后的缩进标签被当成代码块。"""
import re
import sys
from pathlib import Path

ROOT = Path('D:/idea/mini-spring/docs')
bad = []

for md in sorted(ROOT.rglob('*.md')):
    if 'node_modules' in md.parts or '.vitepress' in md.parts:
        continue
    lines = md.read_text(encoding='utf-8').splitlines()
    depth = 0
    start = None
    hits = []
    for i, ln in enumerate(lines, 1):
        if re.match(r'^<section\b', ln):
            depth += 1
            if depth == 1:
                start = i
        elif re.match(r'^</section>', ln) and depth:
            depth -= 1
            if depth == 0:
                start = None
        elif depth >= 1 and ln.strip() == '' and start is not None:
            hits.append(i)
    if hits:
        bad.append((md, start, hits))

if not bad:
    print('OK — 未发现 HTML 块内空行')
else:
    for md, start, hits in bad:
        rel = md.relative_to(ROOT)
        print(f'{rel}: 从第 {start} 行起的 <section> 块内存在空行 → 行 {hits}')
    print(f'\n共 {len(bad)} 个文件需要修复')
