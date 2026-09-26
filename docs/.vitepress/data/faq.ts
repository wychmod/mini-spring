/**
 * 常见问题（FAQ）数据
 * 按主题分组，供 DsFaq 渲染为可搜索的折叠面板
 */

export interface FaqItem {
  q: { zh: string; en: string }
  /** 支持内联 HTML（<code> / <b> / <a>） */
  a: { zh: string; en: string }
}

export interface FaqGroup {
  title: { zh: string; en: string }
  items: FaqItem[]
}

export const faq: FaqGroup[] = [
  {
    title: { zh: '项目与选择', en: 'Project & Choice' },
    items: [
      {
        q: { zh: '这个项目和真实的 Spring 差多少？', en: 'How far is this from real Spring?' },
        a: {
          zh: '看和谁比。mini-spring-iteration 保留了 <code>BeanFactory</code> 继承树、三级缓存、<code>BeanPostProcessor</code> 扩展点、事件广播与类型转换体系，主干设计与 Spring 一致；省略的是 <code>@Transactional</code>、<code>@Configuration</code> 全注解驱动、条件装配等外围能力。它的目标是让你看清主干，而不是覆盖全部 API。',
          en: 'It depends on the baseline. mini-spring-iteration keeps the <code>BeanFactory</code> hierarchy, the three-level cache, <code>BeanPostProcessor</code> extension points, event multicasting and type conversion — the backbone matches Spring. What is omitted is the periphery: <code>@Transactional</code>, fully annotation-driven <code>@Configuration</code>, conditional assembly. The goal is a clear backbone, not full API coverage.',
        },
      },
      {
        q: { zh: '两条实现路径必须都学吗？', en: 'Do I have to read both paths?' },
        a: {
          zh: '不必。有 Spring 使用经验的人可以直接进入 <a href="./compare">iteration</a>；第一次接触的人建议先用 original 建立直觉。想一次到位就按 <a href="./roadmap">学习路线</a> 里的「标准版」顺序走。',
          en: 'No. If you already use Spring, jump straight into <a href="./compare">iteration</a>; if this is your first contact, start with original for intuition. For the complete route, follow the "Standard" track in the <a href="./roadmap">roadmap</a>.',
        },
      },
      {
        q: { zh: '看之前需要先精通 Spring 吗？', en: 'Do I need to master Spring first?' },
        a: {
          zh: '不需要，但需要会写基本的 Spring 代码（<code>@Autowired</code>、<code>@Component</code>、XML 配置）。用过就好，不必精通。',
          en: 'No, but you should be comfortable writing basic Spring code (<code>@Autowired</code>, <code>@Component</code>, XML config). Used-it-before is enough.',
        },
      },
    ],
  },
  {
    title: { zh: '环境与运行', en: 'Setup & Run' },
    items: [
      {
        q: { zh: '需要什么 JDK 版本？', en: 'Which JDK version do I need?' },
        a: {
          zh: 'JDK 8 及以上均可，已在 8 / 11 / 17 上验证。构建工具使用 Maven 3.6+。',
          en: 'JDK 8 or newer works — verified on 8 / 11 / 17. The build uses Maven 3.6+.',
        },
      },
      {
        q: { zh: '为什么 CircleTest 不需要任何配置就能跑？', en: 'Why does CircleTest run with no config?' },
        a: {
          zh: '它是一个独立的 <code>main</code> 方法，用最原始的反射手动演示「实例化 → 入缓存 → 属性填充」的顺序，不经过完整容器，所以不需要 XML 或注解配置。',
          en: 'It is a standalone <code>main</code> method that uses raw reflection to demonstrate the order "instantiate → cache → populate" without the full container, so it needs no XML or annotations.',
        },
      },
      {
        q: { zh: '仓库里的 spring.xml 为什么是注释状态？', en: 'Why is spring.xml commented out in the repo?' },
        a: {
          zh: '它作为样例文件保留，避免影响其他测试。想跑通容器相关测试，取消注释或写入最小 <code>&lt;bean&gt;</code> 配置即可。详见 <a href="./guide/start/troubleshooting">常见报错排查</a>。',
          en: 'It is kept as a sample file so it does not affect other tests. To run container tests, uncomment it or add a minimal <code>&lt;bean&gt;</code> config. See <a href="./guide/start/troubleshooting">Troubleshooting</a>.',
        },
      },
    ],
  },
  {
    title: { zh: '原理疑问', en: 'Design Questions' },
    items: [
      {
        q: {
          zh: '为什么三级缓存要存工厂，而不是直接存对象？',
          en: 'Why does the third-level cache store a factory instead of an object?',
        },
        a: {
          zh: '因为只有真正发生循环依赖时，才需要为这个 Bean 提前生成代理对象。如果第三级直接存对象，就等于每次创建都提前生成代理，会破坏 AOP 的织入时机。存 <code>ObjectFactory</code> 可以把「生成代理」推迟到第一次被引用时。',
          en: 'Because an early proxy is only needed when a circular dependency actually occurs. Storing the object directly would force a proxy to be created on every instantiation, breaking AOP weaving timing. Storing an <code>ObjectFactory</code> defers proxy creation until the first reference.',
        },
      },
      {
        q: {
          zh: 'BeanPostProcessor 和 BeanFactoryPostProcessor 有什么区别？',
          en: 'What is the difference between BeanPostProcessor and BeanFactoryPostProcessor?',
        },
        a: {
          zh: '<code>BeanFactoryPostProcessor</code> 在<strong>实例化之前</strong>运行，操作的是 <code>BeanDefinition</code> 元数据（例如替换 <code>${}</code> 占位符）；<code>BeanPostProcessor</code> 在<strong>每个 Bean 初始化前后</strong>运行，操作的是真实实例（AOP 代理就在这一步织入）。',
          en: '<code>BeanFactoryPostProcessor</code> runs <strong>before instantiation</strong> and works on <code>BeanDefinition</code> metadata (e.g. resolving <code>${}</code> placeholders); <code>BeanPostProcessor</code> runs <strong>around each bean&apos;s initialization</strong> and works on the real instance (AOP proxies are woven here).',
        },
      },
      {
        q: { zh: 'AOP 什么时候用 JDK 代理，什么时候用 CGLIB？', en: 'When does AOP use JDK proxies vs CGLIB?' },
        a: {
          zh: '目标类实现了接口时优先 JDK 动态代理（基于接口生成）；没有实现接口时降级为 CGLIB 子类代理。<code>ProxyFactory</code> 会自动判断，也可以强制指定。',
          en: 'If the target implements an interface, a JDK dynamic proxy is preferred; otherwise it falls back to a CGLIB subclass proxy. <code>ProxyFactory</code> decides automatically and can be forced either way.',
        },
      },
    ],
  },
  {
    title: { zh: '参与与反馈', en: 'Contribute & Feedback' },
    items: [
      {
        q: { zh: '我发现文档写错了，怎么反馈？', en: 'I found a doc error — how do I report it?' },
        a: {
          zh: '直接在 GitHub 上提 Issue，或者按 <a href="./contributing">贡献指南</a> 提交 PR。文档源码就在 <code>docs/</code> 目录下，改起来很快。',
          en: 'Open an issue on GitHub, or submit a PR following the <a href="./contributing">contributing guide</a>. The docs live under <code>docs/</code> and are quick to edit.',
        },
      },
      {
        q: { zh: '项目可以商用吗？', en: 'Can I use it commercially?' },
        a: {
          zh: '可以。项目基于 Apache License 2.0 发布，允许商用、修改与再分发，保留版权声明即可。',
          en: 'Yes. It is released under the Apache License 2.0, permitting commercial use, modification and redistribution as long as the copyright notice is kept.',
        },
      },
    ],
  },
]
