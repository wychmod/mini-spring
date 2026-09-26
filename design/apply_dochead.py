"""为现有文档页统一插入 <DsDocHead> 页头（替换 markdown 的 H1 + 导语段落）。

- 导语优先沿用原页面 H1 之后的第一段（保留 **粗体**、`代码` 等 markdown 内联语法）
- 个别页面原本没有导语，则由 LEDE_OVERRIDE 提供
- 幂等：已包含 <DsDocHead 的文件跳过
"""
import re
from pathlib import Path

ROOT = Path('D:/idea/mini-spring/docs')

# (group, 预计阅读分钟, 涉及类数)
META = {
    'guide/preface.md': ('开始', 4, 4),
    'guide/getting-started.md': ('快速开始', 4, 2),
    'guide/advanced/events.md': ('进阶特性', 4, 8),
    'guide/ioc/bootstrap.md': ('IoC 容器', 4, 25),
    'guide/ioc/circular-deps.md': ('IoC 容器', 4, 3),
    'guide/ioc/class-diagram.md': ('IoC 容器', 4, 16),
    'guide/ioc/lifecycle.md': ('IoC 容器', 5, 23),
    'en/guide/preface.md': ('Start', 4, 4),
    'en/guide/getting-started.md': ('Getting Started', 4, 2),
    'en/guide/aop.md': ('Advanced', 6, 18),
    'en/guide/advanced/events.md': ('Advanced', 4, 8),
    'en/guide/ioc/bootstrap.md': ('IoC Container', 4, 25),
    'en/guide/ioc/circular-deps.md': ('IoC Container', 4, 3),
    'en/guide/ioc/class-diagram.md': ('IoC Container', 4, 18),
    'en/guide/ioc/lifecycle.md': ('IoC Container', 5, 23),
}

LEDE_OVERRIDE = {
    'guide/getting-started.md': '从克隆仓库到跑通第一个测试用例，这一页把环境要求、目录结构与常见报错一次说清。',
    'en/guide/getting-started.md': 'From cloning the repo to running your first test — requirements, project layout and common pitfalls in one page.',
}


def esc(s: str) -> str:
    return s.replace('&', '&amp;').replace('"', '&quot;')


def process(rel: str, group: str, minutes: int, classes: int) -> str:
    p = ROOT / rel
    text = p.read_text(encoding='utf-8')
    if '<DsDocHead' in text:
        return f'{rel}: 已有页头，跳过'

    lines = text.split('\n')
    h1 = next((i for i, l in enumerate(lines) if re.match(r'^# \S', l)), None)
    if h1 is None:
        return f'{rel}: 未找到 H1，跳过'
    title = lines[h1][2:].strip()

    # 收集 H1 之后的第一段作为导语
    j = h1 + 1
    while j < len(lines) and lines[j].strip() == '':
        j += 1
    lede_lines = []
    while j < len(lines) and lines[j].strip() != '':
        if re.match(r'^(#{1,6} |::: |!\[|<|\||- |\* |\d+\. |```)', lines[j]):
            break
        lede_lines.append(lines[j].strip())
        j += 1
    lede = ' '.join(lede_lines).strip()
    if rel in LEDE_OVERRIDE:
        lede = LEDE_OVERRIDE[rel]
    if not lede:
        return f'{rel}: 未能提取导语且无覆盖值，跳过'

    if rel.startswith('en/'):
        meta_items = f"'{minutes} min read', '{classes} classes'"
    else:
        meta_items = f"'预计阅读 {minutes} 分钟', '涉及 {classes} 个类'"

    block = (
        f'<DsDocHead title="{esc(title)}" group="{esc(group)}" '
        f':meta="[{meta_items}]">\n\n'
        f'{lede}\n\n'
        f'</DsDocHead>'
    )

    head = lines[:h1]
    tail = lines[j:]
    new = '\n'.join(head) + '\n' + block + '\n\n' + '\n'.join(tail).lstrip('\n')
    p.write_text(new, encoding='utf-8')
    return f'{rel}: 已插入页头（{title}）'


for rel, (group, minutes, classes) in META.items():
    print(process(rel, group, minutes, classes))
