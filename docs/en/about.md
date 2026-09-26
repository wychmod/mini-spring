---
layout: page
sidebar: false
title: About mini-spring
description: A project written to genuinely understand Spring
---

<DsDocHead title="About mini-spring" :meta="['Apache-2.0', '2 modules', '127 framework classes']">

A project written to genuinely *understand* Spring — not another tutorial, but taking the source
apart and reassembling it by hand.

</DsDocHead>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Story</span><h2 class="ds-heading">Why this exists</h2></div>
<div style="max-width: 680px; font-size: 15px; line-height: 1.85; color: var(--ds-text-2)">
<p style="margin: 0 0 20px">After a few years with Spring I could type <code>@Autowired</code> with my eyes closed — until someone asked why circular dependencies need a three-level cache rather than two, and all I had was a memorised answer. What finally pushed me over the edge was the frustration of reading the source: inside <code>refresh()</code>, twelve methods nest layer after layer with no visible bottom. So instead of grinding through 150,000 lines, I decided to write a few hundred lines of my own. Only after wiring <code>BeanDefinition</code> registration, instantiation, population and initialisation by hand did it actually click.</p>
<p style="margin: 0">Along the way it became clear there is more than one way to write a "mini" version. Some people want the shortest possible path to intuition; others want names and layering that match Spring exactly, comparable section by section. So it ended up as two modules: <b>mini-spring-original</b> explains the backbone in 29 classes, and <b>mini-spring-iteration</b> fills in every extension point across 98. Both share the same design philosophy — start wherever your experience puts you.</p>
</div>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Books</span><h2 class="ds-heading">Companion books</h2><p class="ds-sub">Each module pairs with a book; the code maps chapter by chapter.</p></div>
<div class="ds-books">
<a class="ds-book" href="https://book.douban.com/subject/35006215/" target="_blank" rel="noreferrer"><img :src="$href('/images/book1.png')" alt="Spring 5 Core Principles book cover"><span class="info"><span class="name">"Spring 5 Core Principles — Hand-writing 30 Classes"</span><span class="note">Pairs with mini-spring-original · minimal</span></span></a>
<a class="ds-book" href="https://book.douban.com/subject/36331424/" target="_blank" rel="noreferrer"><img :src="$href('/images/book2.png')" alt="Hand-writing Spring book cover"><span class="info"><span class="name">"Hand-writing Spring: Progressive Source Practice"</span><span class="note">Pairs with mini-spring-iteration · progressive</span></span></a>
</div>
</div>
</section>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Stack</span><h2 class="ds-heading">Technology choices</h2></div>
<div class="ds-grid ds-grid--3">
<article class="ds-card"><span class="ds-icon-box">◈</span><h3>VitePress</h3><p>The docs are built with VitePress. Vue components drop into markdown seamlessly, so the class index, figure gallery and roadmap are interactive components rather than screenshots.</p></article>
<article class="ds-card"><span class="ds-icon-box">◆</span><h3>Java 8</h3><p>The framework targets Java 8 — the same baseline Spring 5 requires. Keeping the language level identical means every "why is it written this way" discussion happens in the same context.</p></article>
<article class="ds-card"><span class="ds-icon-box">◇</span><h3>Bilingual by design</h3><p>Chinese and English routes share the same components, reading from a single <code>classes.ts</code>. Translations never drift — change one place and both update.</p></article>
</div>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Thanks</span><h2 class="ds-heading">Acknowledgements</h2><p class="ds-sub">Thanks to everyone who filed an issue, sent a PR, or pointed out a mistake.</p></div>
<ul class="ds-avatars"><li title="wychmod">wy</li><li title="contributor">+1</li><li title="contributor">+2</li><li title="contributor">+3</li><li title="contributor">+4</li><li title="contributor">+5</li><li title="contributor">+6</li><li title="contributor">+7</li></ul>
</div>
</section>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Contact</span><h2 class="ds-heading">Get in touch</h2></div>
<div class="ds-grid ds-grid--3">
<div class="ds-mini-card"><span class="k">GitHub</span><span class="v mono">@wychmod</span></div>
<div class="ds-mini-card"><span class="k">Issues</span><span class="v mono">Questions &amp; ideas</span></div>
<div class="ds-mini-card"><span class="k">License</span><span class="v mono">Apache-2.0</span></div>
</div>
<div class="ds-btn-row" style="margin-top: 28px">
<a class="ds-btn ds-btn--primary" href="https://github.com/wychmod/mini-spring" target="_blank" rel="noreferrer">View source</a>
<a class="ds-btn ds-btn--ghost" :href="$href('/en/contributing')">Contributing →</a>
</div>
</section>
