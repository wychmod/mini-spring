---
layout: page
sidebar: false
title: Changelog
description: Release history of mini-spring
---

<DsDocHead narrow title="Changelog" :meta="['2 modules', '4 releases', 'Apache-2.0']">

Every release in reverse chronological order. For older changes, see GitHub Releases.

</DsDocHead>

<section class="ds-section ds-section--narrow">
<div class="ds-version"><div class="ds-version-head"><span class="ver">v2.0</span><span class="date">2026-09-26</span><span class="ds-badge ds-badge--accent">Latest</span></div>
<ul class="ds-changes">
<li><span class="ds-badge ds-badge--success">New</span>A bilingual documentation site, every chapter mirrored in English</li>
<li><span class="ds-badge ds-badge--success">New</span>Five architecture diagrams redrawn as scalable SVG</li>
<li><span class="ds-badge ds-badge--success">New</span>Class index: one-line responsibilities for all 127 framework classes</li>
<li><span class="ds-badge ds-badge--success">New</span>Comparison, roadmap, FAQ and troubleshooting pages</li>
<li><span class="ds-badge ds-badge--violet">Refactor</span>Documentation restructured into portal / routing / docs / support layers</li>
<li><span class="ds-badge ds-badge--warning">Fix</span>Removed a duplicated class from the index; count corrected from 129 to 127</li>
</ul>
</div>
<div class="ds-version"><div class="ds-version-head"><span class="ver">v1.2</span><span class="date">2026-08-18</span></div>
<ul class="ds-changes">
<li><span class="ds-badge ds-badge--success">New</span>Event multicasting: <code>ApplicationEventMulticaster</code> and lifecycle events</li>
<li><span class="ds-badge ds-badge--success">New</span>The <code>ConversionService</code> type-conversion system</li>
<li><span class="ds-badge ds-badge--success">New</span><code>BeanFactoryPostProcessor</code> extension point</li>
<li><span class="ds-badge ds-badge--warning">Fix</span>Ordering of <code>Aware</code> callbacks during property population</li>
</ul>
</div>
<div class="ds-version"><div class="ds-version-head"><span class="ver">v1.1</span><span class="date">2026-07-20</span></div>
<ul class="ds-changes">
<li><span class="ds-badge ds-badge--success">New</span>Advisor auto-weaving: <code>DefaultAdvisorAutoProxyCreator</code></li>
<li><span class="ds-badge ds-badge--success">New</span><code>@Value</code> / <code>@Qualifier</code> annotation support</li>
<li><span class="ds-badge ds-badge--success">New</span>Complete three-level cache for field-injection circular dependencies</li>
</ul>
</div>
<div class="ds-version"><div class="ds-version-head"><span class="ver">v1.0</span><span class="date">2026-06-15</span></div>
<ul class="ds-changes">
<li><span class="ds-badge ds-badge--success">New</span>First usable release: IoC + DI + AOP backbone working</li>
<li><span class="ds-badge ds-badge--success">New</span><code>CircleTest</code> circular-dependency demo</li>
<li><span class="ds-badge ds-badge--success">New</span>The minimal MVC module, mini-spring-original</li>
</ul>
</div>
<div class="ds-btn-row" style="margin-top: 28px">
<a class="ds-btn ds-btn--ghost ds-btn--sm" href="https://github.com/wychmod/mini-spring/releases" target="_blank" rel="noreferrer">Older changes on GitHub Releases →</a>
</div>
</section>
