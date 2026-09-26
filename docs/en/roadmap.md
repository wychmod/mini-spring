---
layout: page
sidebar: false
title: Learning Roadmap
description: Three tracks — Quick 1 hour / Standard 1 week / Deep 3 weeks, pick by your time budget
---

<section class="ds-section ds-section--wide">
  <header class="ds-pagehead" style="padding-top: 0">
    <nav class="ds-breadcrumb" aria-label="Breadcrumb">
      <a :href="$href('/en/')">Home</a>
      <span>/</span>
      <span>Roadmap</span>
    </nav>
    <h1>Learning Roadmap</h1>
    <p class="ds-lede">
      Three tracks, chosen by your time budget. Every node spells out a concrete output and a check —
      not "read this chapter", but "after this chapter you can do X".
    </p>
  </header>
  <DsRoadmap />
</section>

<section class="ds-section ds-section--wide ds-section--alt">
  <div class="ds-section-inner">
    <div class="ds-section-head">
      <span class="ds-eyebrow">At a Glance</span>
      <h2 class="ds-heading">The three tracks compared</h2>
      <p class="ds-sub">Same content, three ways to read it. Wrong pick? Just move up a track.</p>
    </div>
    <div class="ds-grid ds-grid--3">
      <article class="ds-card" style="--ds-card-accent: #27c9ff; --ds-card-accent-soft: rgba(39,201,255,.12)">
        <span class="tag">Quick</span>
        <h3>1 hour</h3>
        <p>Diagrams and conclusions only, skipping source-level detail. Great for refreshing concepts before an interview.</p>
        <ul style="margin: 12px 0 0; padding-left: 18px; font-size: 13.5px; color: var(--ds-text-2)">
          <li>Five architecture diagrams</li>
          <li>An overview of the nine refresh() steps</li>
          <li>A one-liner on the three-level cache</li>
        </ul>
      </article>
      <article class="ds-card" style="--ds-card-accent: #2e63e7; --ds-card-accent-soft: rgba(46,99,231,.12)">
        <span class="tag">Standard</span>
        <h3>1 week</h3>
        <p>Read every doc and run the tests, walking the container from bootstrap to destruction.</p>
        <ul style="margin: 12px 0 0; padding-left: 18px; font-size: 13.5px; color: var(--ds-text-2)">
          <li>The nine-step bootstrap call chain</li>
          <li>Three property-population paths</li>
          <li>How the three-level cache degrades</li>
        </ul>
      </article>
      <article class="ds-card" style="--ds-card-accent: #7c5ce8; --ds-card-accent-soft: rgba(124,92,232,.12)">
        <span class="tag">Deep</span>
        <h3>3 weeks</h3>
        <p>Compare line by line with real Spring source, and find what the mini version omits — and why it can.</p>
        <ul style="margin: 12px 0 0; padding-left: 18px; font-size: 13.5px; color: var(--ds-text-2)">
          <li>Every refresh() line and its class</li>
          <li>Hand-write the cache degradation</li>
          <li>Custom Advisor and events</li>
        </ul>
      </article>
    </div>
    <div class="ds-btn-row" style="margin-top: 32px">
      <a class="ds-btn ds-btn--primary" :href="$href('/en/guide/preface')">Start the first track →</a>
      <a class="ds-btn ds-btn--ghost" :href="$href('/en/compare')">Compare the two paths →</a>
    </div>
  </div>
</section>
