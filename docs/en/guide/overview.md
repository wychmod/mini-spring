---
layout: page
sidebar: false
title: "Overview: Two Paths"
---

<DsDocHead title="Overview: Two Paths" :meta="['6 min read', '2 modules', '127 framework classes']">

The same Spring ideas at two very different paces. This page looks at the two paths from a
**code-structure** angle — to pick one to study, head to [Compare the paths](/en/compare).

</DsDocHead>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">At a Glance</span><h2 class="ds-heading">Module comparison</h2><p class="ds-sub">Build intuition first, then dig into details — or pick by experience and skip ahead.</p></div>
<table class="ds-compare-table"><thead><tr><th style="width: 24%">Dimension</th><th style="width: 38%">mini-spring-original</th><th style="width: 38%">mini-spring-iteration</th></tr></thead><tbody><tr><td data-label="Dimension">Focus</td><td data-label="original">A minimal version in 29 classes</td><td data-label="iteration">A progressive implementation in 98 classes</td></tr><tr><td data-label="Dimension">Naming</td><td data-label="original">Independent names, very short chains</td><td data-label="iteration">Same names and layering as Spring</td></tr><tr><td data-label="Dimension">Entry point</td><td data-label="original">Annotation scanning + DispatchServlet</td><td data-label="iteration">ClassPathXmlApplicationContext + refresh()</td></tr><tr><td data-label="Dimension">Configuration</td><td data-label="original">Annotation scanning</td><td data-label="iteration">XML + annotation scanning</td></tr><tr><td data-label="Dimension">AOP</td><td data-label="original">Dual proxies + interception chain</td><td data-label="iteration">Dual proxies + Advisor auto-weaving</td></tr><tr><td data-label="Dimension">Circular deps</td><td data-label="original">One-level cache demo</td><td data-label="iteration">Full three-level cache</td></tr><tr><td data-label="Dimension">Best for</td><td data-label="original">First contact — build intuition</td><td data-label="iteration">After one pass — dig into details</td></tr></tbody></table>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Packages</span><h2 class="ds-heading">Package structure: flat vs deep</h2><p class="ds-sub">original sits flat in 4 functional packages; iteration unfolds into 6 layered packages following Spring.</p></div>
<div class="ds-grid ds-grid--2"><pre class="ds-tree"><span class="root">mini-spring-original</span>
framework/
<span class="conn"> ├─</span> annotation/   5 classes
<span class="conn"> ├─</span> aop/         14 classes
<span class="conn"> ├─</span> beans/        3 classes
<span class="conn"> └─</span> webmvc/       7 classes
</pre><pre class="ds-tree"><span class="root">mini-spring-iteration</span>
springframework/
<span class="conn"> ├─</span> beans/       41 classes
<span class="conn"> ├─</span> context/     21 classes
<span class="conn"> ├─</span> aop/         18 classes
<span class="conn"> ├─</span> core/        14 classes
<span class="conn"> ├─</span> stereotype/   1 class
<span class="conn"> └─</span> utils/        3 classes
</pre></div>
<div class="ds-btn-row" style="margin-top: 24px"><a class="ds-btn ds-btn--ghost ds-btn--sm" :href="$href('/en/guide/start/structure')">Project Structure →</a><a class="ds-btn ds-btn--ghost ds-btn--sm" :href="$href('/en/reference')">Class Index →</a></div>
</div>
</section>

<section class="ds-section ds-section--wide">
<div class="ds-section-head"><span class="ds-eyebrow">Coverage</span><h2 class="ds-heading">Feature coverage matrix</h2><p class="ds-sub">iteration covers the backbone; original adds a minimal MVC layer but omits the cache and extension points.</p></div>
<table class="ds-compare-table"><thead><tr><th style="width: 40%">Capability</th><th style="width: 30%">mini-spring-original</th><th style="width: 30%">mini-spring-iteration</th></tr></thead><tbody><tr><td data-label="Capability">IoC container</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="Capability">Dependency injection</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="Capability">AOP</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="Capability">Annotation scanning</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="Capability">MVC support</td><td data-label="original"><span class="ds-badge ds-badge--success">✓</span></td><td data-label="iteration"><span class="ds-badge ds-badge--plain">—</span></td></tr><tr><td data-label="Capability">Three-level cache</td><td data-label="original"><span class="ds-badge ds-badge--plain">—</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="Capability">Event multicasting</td><td data-label="original"><span class="ds-badge ds-badge--plain">—</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="Capability">Type conversion</td><td data-label="original"><span class="ds-badge ds-badge--plain">—</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr><tr><td data-label="Capability">Extension points</td><td data-label="original"><span class="ds-badge ds-badge--plain">—</span></td><td data-label="iteration"><span class="ds-badge ds-badge--success">✓</span></td></tr></tbody></table>
</section>

<section class="ds-section ds-section--wide ds-section--alt">
<div class="ds-section-inner">
<div class="ds-section-head"><span class="ds-eyebrow">Next</span><h2 class="ds-heading">Where to go next</h2></div>
<div class="ds-grid ds-grid--3"><article class="ds-card"><span class="tag">RUN</span><h3>Get it running</h3><p>Requirements, layout and your first test case.</p><a class="ds-card-link" :href="$href('/en/guide/getting-started')">Setup &amp; Run →</a></article><article class="ds-card"><span class="tag">CHOOSE</span><h3>Still deciding</h3><p>A full 16-dimension comparison with a decision tree.</p><a class="ds-card-link" :href="$href('/en/compare')">Compare the paths →</a></article><article class="ds-card"><span class="tag">PLAN</span><h3>Want a plan</h3><p>Quick 1 hour / Standard 1 week / Deep 3 weeks.</p><a class="ds-card-link" :href="$href('/en/roadmap')">Learning roadmap →</a></article></div>
</div>
</section>
