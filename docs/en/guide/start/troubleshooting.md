---
title: Troubleshooting
---

<DsDocHead title="Troubleshooting" group="Getting Started" :meta="['4 min read', '8 common issues']">

Find the answer by the message you see. Type a keyword to filter instantly — symptom, cause and fix line up row by row.

</DsDocHead>

<DsErrorTable />

## Still stuck?

<div class="ds-grid ds-grid--2">
<article class="ds-card"><span class="tag">ASK</span><h3>Ask on GitHub</h3><p>Paste the full error, your JDK version and the command you ran — that gets the fastest answer.</p><a class="ds-card-link" href="https://github.com/wychmod/mini-spring/issues" target="_blank" rel="noreferrer">Open an issue →</a></article>
<article class="ds-card"><span class="tag">FAQ</span><h3>Check the FAQ</h3><p>Conceptual questions such as "why does the third-level cache store a factory" are answered there.</p><a class="ds-card-link" :href="$href('/en/faq')">FAQ →</a></article>
</div>

## How to troubleshoot

::: tip Check three things first
1. **Right module** — run `mvn test -pl mini-spring-iteration` to pin the module and avoid ambiguity in the aggregator build.
2. **Resource on the classpath** — `spring.xml` must sit in `src/main/resources`, or the `classpath:` prefix cannot resolve it.
3. **Annotation processing on** — in IntelliJ, tick `Settings → Build → Compiler → Annotation Processors`.
:::

::: warning About circular-dependency hangs
If a test hangs on two beans that depend on each other, first check that `addSingletonFactory` is called **before** property population.
Get the order wrong and the half-product never reaches the third-level cache, so `getBean` recurses forever.
:::
