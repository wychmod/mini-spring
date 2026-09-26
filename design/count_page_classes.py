"""统计每篇文档正文中实际出现的框架类名数量（去重）。

用真实的「出现次数」作为页头元信息，避免臆造数字。
"""
import re
from pathlib import Path

ROOT = Path('D:/idea/mini-spring/docs')
data = (ROOT / '.vitepress/data/classes.ts').read_text(encoding='utf-8')
names = sorted({m for m in re.findall(r"name: '([A-Za-z0-9_]+)'", data)}, key=len, reverse=True)

PAGES = [
    'guide/preface.md', 'guide/overview.md', 'guide/getting-started.md',
    'guide/aop.md', 'guide/advanced/events.md',
    'guide/ioc/bootstrap.md', 'guide/ioc/circular-deps.md',
    'guide/ioc/class-diagram.md', 'guide/ioc/lifecycle.md',
    'en/guide/preface.md', 'en/guide/overview.md', 'en/guide/getting-started.md',
    'en/guide/aop.md', 'en/guide/advanced/events.md',
    'en/guide/ioc/bootstrap.md', 'en/guide/ioc/circular-deps.md',
    'en/guide/ioc/class-diagram.md', 'en/guide/ioc/lifecycle.md',
]

for rel in PAGES:
    p = ROOT / rel
    if not p.exists():
        print(f'{rel}: (缺失)')
        continue
    text = p.read_text(encoding='utf-8')
    found = {n for n in names if re.search(r'\b' + re.escape(n) + r'\b', text)}
    print(f'{rel}: 涉及 {len(found)} 个类')
