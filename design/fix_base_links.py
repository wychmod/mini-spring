#!/usr/bin/env python3
"""
把 markdown 原始 HTML 里手写的 href="/xxx" 改写成 :href="$withBase('/xxx')"。

背景：VitePress 只对 markdown 语法链接自动补 base；原始 HTML 中的 href 不会补，
在 base = '/mini-spring/' 的部署下，直开/新标签页打开会 404。

用法：
    python design/fix_base_links.py          # 预览
    python design/fix_base_links.py --write  # 实际写入
"""
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent / "docs"
WRITE = "--write" in sys.argv

# href="/path"  —— 排除 href="//host"（协议相对）和已带 base 的链接
PATTERN = re.compile(r'href="(/[^"]*)"')


def skip(value: str) -> bool:
    return value.startswith("//") or value.startswith("/mini-spring")


def main() -> int:
    files = sorted(p for p in ROOT.rglob("*.md") if "node_modules" not in p.parts and "dist" not in p.parts)
    total = 0
    touched = 0
    for path in files:
        src = path.read_text(encoding="utf-8")
        hits = []

        def repl(m: "re.Match[str]") -> str:
            value = m.group(1)
            if skip(value):
                return m.group(0)
            hits.append(value)
            return f':href="$withBase(\'{value}\')"'

        out = PATTERN.sub(repl, src)
        if not hits:
            continue
        touched += 1
        total += len(hits)
        rel = path.relative_to(ROOT).as_posix()
        print(f"{rel}  x{len(hits)}")
        for h in hits:
            print(f"    {h}")
        if WRITE:
            path.write_text(out, encoding="utf-8")

    print(f"\n{'WROTE' if WRITE else 'DRY-RUN'}: files={touched} links={total}")
    if not WRITE:
        print("用 --write 实际写入")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
