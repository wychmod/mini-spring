---
layout: page
sidebar: false
title: Contributing
description: Code, docs or issues — all contributions are welcome
---

<DsDocHead title="Contributing" :meta="['5-step flow', 'Code conventions', 'Doc glossary']">

Code, docs or issues — all contributions are welcome. This page spells out the conventions so your
first submission goes through.

</DsDocHead>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Workflow</span><h2 class="ds-heading">Contribution flow</h2><p class="ds-sub">Five steps, well under ten minutes.</p></div>
<div class="ds-steps ds-steps--5">
<div class="ds-step"><span class="num">01</span><h4>Fork</h4><p>Fork the repository into your own GitHub account.</p></div>
<div class="ds-step"><span class="num">02</span><h4>Branch</h4><p>Name by type: <code>feat/xxx</code> or <code>fix/xxx</code>.</p></div>
<div class="ds-step"><span class="num">03</span><h4>Change</h4><p>Keep the existing style. Code and docs both count.</p></div>
<div class="ds-step"><span class="num">04</span><h4>Test</h4><p><code>mvn test</code> must pass locally.</p></div>
<div class="ds-step"><span class="num">05</span><h4>Open a PR</h4><p>Describe the change and link the related issue.</p></div>
</div>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Conventions</span><h2 class="ds-heading">Code conventions</h2></div>
<table class="ds-compare-table"><thead><tr><th style="width: 22%">Item</th><th style="width: 40%">Convention</th><th style="width: 38%">Example</th></tr></thead><tbody><tr><td data-label="Item">Naming</td><td data-label="Convention">Match Spring's source; do not invent terms</td><td data-label="Example"><code>DefaultSingletonBeanRegistry</code></td></tr><tr><td data-label="Item">Comments</td><td data-label="Convention">Every class and method has a comment explaining "why"</td><td data-label="Example"><code>/** level-1 cache */</code></td></tr><tr><td data-label="Item">Packages</td><td data-label="Convention">Layered by feature; no reverse cross-layer references</td><td data-label="Example"><code>beans</code> / <code>context</code> / <code>aop</code></td></tr><tr><td data-label="Item">Commit message</td><td data-label="Convention">type(scope): description</td><td data-label="Example"><code>feat(aop): pointcut expressions</code></td></tr><tr><td data-label="Item">Doc changes</td><td data-label="Convention">Update Chinese and English together</td><td data-label="Example"><code>docs/</code> + <code>docs/en/</code></td></tr></tbody></table>
</div>
</section>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Glossary</span><h2 class="ds-heading">Documentation glossary</h2><p class="ds-sub">One agreed translation per concept, so wording never drifts.</p></div>
<table class="ds-compare-table"><thead><tr><th style="width: 30%">Term</th><th style="width: 70%">Agreed usage</th></tr></thead><tbody><tr><td data-label="Term"><code>BeanDefinition</code></td><td data-label="Usage">Not translated — keep the English term</td></tr><tr><td data-label="Term">early exposure</td><td data-label="Usage">Always "early exposure", not "early reference"</td></tr><tr><td data-label="Term">three-level cache</td><td data-label="Usage">Refer to caches as "level 1 / 2 / 3" consistently</td></tr><tr><td data-label="Term"><code>refresh()</code></td><td data-label="Usage">With parentheses, monospace; the flow is "the nine-step refresh"</td></tr><tr><td data-label="Term">aspect / pointcut / advice</td><td data-label="Usage">Keep the three distinct</td></tr></tbody></table>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Checklist</span><h2 class="ds-heading">PR checklist</h2><p class="ds-sub">Self-check these before submitting — it lifts the first-pass rate a lot.</p></div>
<ul class="ds-checklist"><li><span class="box"></span>The code compiles and <code>mvn test</code> passes</li><li><span class="box"></span>New classes carry explanatory comments</li><li><span class="box"></span>Doc changes are mirrored in English</li><li><span class="box"></span>Commit message follows "type(scope): description"</li><li><span class="box"></span>The related issue is linked</li></ul>
<div class="ds-btn-row" style="margin-top: 28px">
<a class="ds-btn ds-btn--primary" href="https://github.com/wychmod/mini-spring/issues" target="_blank" rel="noreferrer">Browse open issues →</a>
<a class="ds-btn ds-btn--ghost" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">Read the source →</a>
</div>
</div>
</section>
