/**
 * 类参考索引数据
 * 来源：mini-spring 仓库源码逐类核对（iteration 98 类 + original framework 29 类 = 127 类）
 */

export interface ClassEntry {
  module: 'iteration' | 'original'
  pkg: string
  name: string
  desc: { zh: string; en: string }
  chapter?: string
}

/** 章节链接与标签 */
export const chapters: Record<string, { zh: string; en: string }> = {
  bootstrap: { zh: '/guide/ioc/bootstrap', en: '/en/guide/ioc/bootstrap' },
  lifecycle: { zh: '/guide/ioc/lifecycle', en: '/en/guide/ioc/lifecycle' },
  cache: { zh: '/guide/ioc/circular-deps', en: '/en/guide/ioc/circular-deps' },
  diagram: { zh: '/guide/ioc/class-diagram', en: '/en/guide/ioc/class-diagram' },
  aop: { zh: '/guide/aop', en: '/en/guide/aop' },
  events: { zh: '/guide/advanced/events', en: '/en/guide/advanced/events' },
  start: { zh: '/guide/getting-started', en: '/en/guide/getting-started' },
}

export const chapterLabels: Record<string, { zh: string; en: string }> = {
  bootstrap: { zh: '启动流程', en: 'Bootstrap' },
  lifecycle: { zh: '生命周期', en: 'Lifecycle' },
  cache: { zh: '三级缓存', en: 'Cache' },
  diagram: { zh: '类图', en: 'Diagram' },
  aop: { zh: 'AOP', en: 'AOP' },
  events: { zh: '事件', en: 'Events' },
  start: { zh: '快速开始', en: 'Setup' },
}

/** 包职责 */
export const pkgNotes: Record<string, { zh: string; en: string }> = {
  aop: { zh: '切点表达式、通知、双代理与自动织入', en: 'Pointcuts, advice, dual proxies, auto-weaving' },
  beans: { zh: '定义注册、BeanFactory 继承树、三级缓存、生命周期', en: 'Definition registry, BeanFactory hierarchy, three-level cache, lifecycle' },
  context: { zh: '容器入口 refresh()、事件广播、注解扫描', en: 'Container entry refresh(), event multicasting, annotation scanning' },
  core: { zh: 'Resource 抽象与类型转换体系', en: 'Resource abstraction and type conversion' },
  stereotype: { zh: '构造型注解', en: 'Stereotype annotations' },
  utils: { zh: '反射与数字工具', en: 'Reflection and number utilities' },
  annotation: { zh: '极简 MVC 的自定义注解', en: 'Custom annotations for the minimal MVC layer' },
  webmvc: { zh: '极简 MVC：DispatchServlet 与视图解析', en: 'Minimal MVC: DispatchServlet and view resolving' },
}

export const pkgOrder = ['beans', 'context', 'aop', 'core', 'stereotype', 'utils', 'webmvc', 'annotation']

const I = 'iteration'
const O = 'original'

export const registry: ClassEntry[] = [
  // ── iteration / beans (41) ────────────────────────────────────────────
  { module: I, pkg: 'beans', name: 'BeanFactory', desc: { zh: '容器最顶层接口，定义 getBean', en: 'Top-level container interface defining getBean' }, chapter: 'bootstrap' },
  { module: I, pkg: 'beans', name: 'HierarchicalBeanFactory', desc: { zh: '支持父子容器的工厂', en: 'BeanFactory supporting parent-child containers' }, chapter: 'bootstrap' },
  { module: I, pkg: 'beans', name: 'ListableBeanFactory', desc: { zh: '可按类型列举 Bean 的工厂', en: 'BeanFactory that can enumerate beans by type' } },
  { module: I, pkg: 'beans', name: 'AutowireCapableBeanFactory', desc: { zh: '具备自动装配能力的工厂', en: 'BeanFactory capable of autowiring' } },
  { module: I, pkg: 'beans', name: 'ConfigurableBeanFactory', desc: { zh: '可配置的工厂，提供各类后置处理器注册', en: 'Configurable factory with post-processor registration' } },
  { module: I, pkg: 'beans', name: 'ConfigurableListableBeanFactory', desc: { zh: '可配置 + 可列举，容器能力的合集', en: 'Combines configurable and listable capabilities' } },
  { module: I, pkg: 'beans', name: 'BeanDefinition', desc: { zh: 'Bean 的定义元数据：类名、作用域、属性值', en: 'Bean definition metadata: class name, scope, property values' }, chapter: 'bootstrap' },
  { module: I, pkg: 'beans', name: 'BeanDefinitionRegistry', desc: { zh: '定义注册中心，registerBeanDefinition 的入口', en: 'Registry for bean definitions' }, chapter: 'bootstrap' },
  { module: I, pkg: 'beans', name: 'BeanDefinitionReader', desc: { zh: '定义读取器接口', en: 'Bean definition reader interface' } },
  { module: I, pkg: 'beans', name: 'AbstractBeanDefinitionReader', desc: { zh: '读取器的公共骨架，持注册中心与资源加载器', en: 'Common skeleton holding registry and resource loader' } },
  { module: I, pkg: 'beans', name: 'XmlBeanDefinitionReader', desc: { zh: '解析 XML 配置并注册定义', en: 'Parses XML config and registers definitions' }, chapter: 'bootstrap' },
  { module: I, pkg: 'beans', name: 'BeanReference', desc: { zh: 'XML 中 ref 属性的解析结果，延迟解析为真实 Bean', en: 'Resolved ref attribute, dereferenced to a real bean later' } },
  { module: I, pkg: 'beans', name: 'PropertyValue', desc: { zh: '单个属性键值对', en: 'A single property key-value pair' } },
  { module: I, pkg: 'beans', name: 'PropertyValues', desc: { zh: '属性集合，支撑属性填充阶段的遍历', en: 'Property collection driving the populate phase' } },
  { module: I, pkg: 'beans', name: 'DefaultListableBeanFactory', desc: { zh: '完整的默认容器实现，注册中心本体', en: 'Full default container implementation' }, chapter: 'diagram' },
  { module: I, pkg: 'beans', name: 'AbstractBeanFactory', desc: { zh: 'getBean 模板方法与单例/原型分支', en: 'getBean template method and scope branching' }, chapter: 'diagram' },
  { module: I, pkg: 'beans', name: 'AbstractAutowireCapableBeanFactory', desc: { zh: 'createBean 主流程：实例化 → 填充 → 初始化', en: 'createBean main flow: instantiate, populate, initialize' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'SingletonBeanRegistry', desc: { zh: '单例注册接口', en: 'Singleton registration interface' } },
  { module: I, pkg: 'beans', name: 'DefaultSingletonBeanRegistry', desc: { zh: '三级缓存实现，解决循环依赖的核心', en: 'Three-level cache; the heart of circular-dependency solving' }, chapter: 'cache' },
  { module: I, pkg: 'beans', name: 'FactoryBeanRegistrySupport', desc: { zh: 'FactoryBean 产物的缓存支持', en: 'Caching support for FactoryBean products' } },
  { module: I, pkg: 'beans', name: 'FactoryBean', desc: { zh: '生产 Bean 的工厂 Bean，getObject 返回真实对象', en: 'Factory bean whose getObject returns the real bean' } },
  { module: I, pkg: 'beans', name: 'ObjectFactory', desc: { zh: '延迟获取对象的工厂接口，三级缓存的载体', en: 'Lazy object factory; the payload of the third-level cache' }, chapter: 'cache' },
  { module: I, pkg: 'beans', name: 'BeansException', desc: { zh: '容器异常基类', en: 'Base container exception' } },
  { module: I, pkg: 'beans', name: 'BeanPostProcessor', desc: { zh: '介入 Bean 初始化前后，AOP 织入的入口', en: 'Hooks before and after bean initialization; entry for AOP weaving' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'InstantiationAwareBeanPostProcessor', desc: { zh: '在实例化阶段介入，可替换实例', en: 'Intervenes at instantiation, may replace the instance' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'BeanFactoryPostProcessor', desc: { zh: '在实例化前修改 BeanDefinition', en: 'Modifies definitions before instantiation' }, chapter: 'bootstrap' },
  { module: I, pkg: 'beans', name: 'PropertyPlaceholderConfigurer', desc: { zh: '解析配置中的 ${} 占位符', en: 'Resolves ${} placeholders in configuration' }, chapter: 'bootstrap' },
  { module: I, pkg: 'beans', name: 'InstantiationStrategy', desc: { zh: '实例化策略接口', en: 'Instantiation strategy interface' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'SimpleInstantiationStrategy', desc: { zh: '反射实例化策略', en: 'Reflection-based instantiation strategy' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'CglibSubclassingInstantiationStrategy', desc: { zh: 'CGLIB 子类化实例化策略', en: 'CGLIB subclassing instantiation strategy' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'Aware', desc: { zh: '各类 Aware 回调的顶层标记接口', en: 'Marker interface for all Aware callbacks' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'BeanNameAware', desc: { zh: '回调注入 Bean 名称', en: 'Callback injecting the bean name' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'BeanClassLoaderAware', desc: { zh: '回调注入 ClassLoader', en: 'Callback injecting the ClassLoader' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'BeanFactoryAware', desc: { zh: '回调注入 BeanFactory', en: 'Callback injecting the BeanFactory' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'InitializingBean', desc: { zh: '初始化回调 afterPropertiesSet', en: 'Initialization callback afterPropertiesSet' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'DisposableBean', desc: { zh: '销毁回调 destroy', en: 'Destruction callback destroy' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'DisposableBeanAdapter', desc: { zh: '统一适配销毁方法（接口方法或 destroy-method）', en: 'Unifies destruction via interface or destroy-method' }, chapter: 'lifecycle' },
  { module: I, pkg: 'beans', name: 'Autowired', desc: { zh: '@Autowired 注解定义', en: 'The @Autowired annotation' } },
  { module: I, pkg: 'beans', name: 'Value', desc: { zh: '@Value 注解定义', en: 'The @Value annotation' } },
  { module: I, pkg: 'beans', name: 'Qualifier', desc: { zh: '@Qualifier 注解，按名称消歧注入', en: 'The @Qualifier annotation for disambiguating injection' } },
  { module: I, pkg: 'beans', name: 'AutowiredAnnotationBeanPostProcessor', desc: { zh: '处理 @Autowired / @Value / @Qualifier 注入', en: 'Handles @Autowired / @Value / @Qualifier injection' }, chapter: 'lifecycle' },

  // ── iteration / context (21) ──────────────────────────────────────────
  { module: I, pkg: 'context', name: 'ApplicationContext', desc: { zh: '应用上下文接口，整合容器与事件能力', en: 'Application context combining container and event capabilities' }, chapter: 'bootstrap' },
  { module: I, pkg: 'context', name: 'ConfigurableApplicationContext', desc: { zh: '可配置的上下文，暴露 refresh 与 close', en: 'Configurable context exposing refresh and close' }, chapter: 'bootstrap' },
  { module: I, pkg: 'context', name: 'AbstractApplicationContext', desc: { zh: 'refresh() 九步启动的模板方法', en: 'Template method for the nine-step refresh()' }, chapter: 'bootstrap' },
  { module: I, pkg: 'context', name: 'AbstractRefreshableApplicationContext', desc: { zh: '支持重复 refresh 的上下文基类', en: 'Base context supporting repeated refresh' }, chapter: 'bootstrap' },
  { module: I, pkg: 'context', name: 'AbstractXmlApplicationContext', desc: { zh: 'XML 驱动的上下文抽象', en: 'Abstract context driven by XML config' }, chapter: 'bootstrap' },
  { module: I, pkg: 'context', name: 'ClassPathXmlApplicationContext', desc: { zh: '容器入口：XML 配置装配并启动', en: 'Container entry: loads XML config and boots' }, chapter: 'bootstrap' },
  { module: I, pkg: 'context', name: 'ClassPathBeanDefinitionScanner', desc: { zh: '类路径注解扫描器，注册 @Component', en: 'Classpath annotation scanner registering @Component' }, chapter: 'bootstrap' },
  { module: I, pkg: 'context', name: 'ClassPathScanningCandidateComponentProvider', desc: { zh: '候选组件提供者，扫描的基础设施', en: 'Candidate provider underpinning scanning' }, chapter: 'bootstrap' },
  { module: I, pkg: 'context', name: 'ApplicationEvent', desc: { zh: '应用事件基类，携带时间戳', en: 'Base application event carrying a timestamp' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'ApplicationContextEvent', desc: { zh: '上下文事件基类', en: 'Base context event' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'ContextRefreshedEvent', desc: { zh: '容器刷新完成事件', en: 'Fired when the container finishes refresh' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'ContextClosedEvent', desc: { zh: '容器关闭事件', en: 'Fired when the container closes' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'ApplicationEventPublisher', desc: { zh: '事件发布接口', en: 'Event publishing interface' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'ApplicationListener', desc: { zh: '事件监听器接口，onApplicationEvent 响应', en: 'Listener interface responding via onApplicationEvent' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'ApplicationEventMulticaster', desc: { zh: '事件广播器接口', en: 'Event multicaster interface' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'AbstractApplicationEventMulticaster', desc: { zh: '广播器抽象，维护监听器集合', en: 'Abstract multicaster holding the listener set' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'SimpleApplicationEventMulticaster', desc: { zh: '广播器实现，逐个通知监听器', en: 'Multicaster implementation notifying listeners in turn' }, chapter: 'events' },
  { module: I, pkg: 'context', name: 'ApplicationContextAware', desc: { zh: '回调注入 ApplicationContext', en: 'Callback injecting the ApplicationContext' }, chapter: 'lifecycle' },
  { module: I, pkg: 'context', name: 'ApplicationContextAwareProcessor', desc: { zh: '处理 ApplicationContextAware 的注入', en: 'Injects the ApplicationContext into aware beans' }, chapter: 'lifecycle' },
  { module: I, pkg: 'context', name: 'ConversionServiceFactoryBean', desc: { zh: '把转换服务注册为 Bean', en: 'Registers the conversion service as a bean' } },
  { module: I, pkg: 'context', name: 'Scope', desc: { zh: '@Scope 注解，声明单例或原型', en: 'The @Scope annotation declaring singleton or prototype' } },

  // ── iteration / aop (18) ──────────────────────────────────────────────
  { module: I, pkg: 'aop', name: 'Pointcut', desc: { zh: '切点接口，组合类过滤与方法匹配', en: 'Pointcut combining class filter and method matcher' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'ClassFilter', desc: { zh: '类级别过滤器', en: 'Class-level filter' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'MethodMatcher', desc: { zh: '方法级别匹配器', en: 'Method-level matcher' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'AspectJExpressionPointcut', desc: { zh: '基于 AspectJ 表达式的切点实现', en: 'Pointcut backed by AspectJ expressions' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'BeforeAdvice', desc: { zh: '前置通知标记接口', en: 'Marker interface for before-advice' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'MethodBeforeAdvice', desc: { zh: '方法前置通知，before 方法承载增强逻辑', en: 'Method before-advice carrying the enhancement' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'MethodBeforeAdviceInterceptor', desc: { zh: '把前置通知包装成方法拦截器', en: 'Wraps before-advice into a method interceptor' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'Advisor', desc: { zh: '通知器：把切点与通知组装在一起', en: 'Advisor assembling a pointcut with advice' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'PointcutAdvisor', desc: { zh: '带切点的通知器', en: 'Advisor carrying a pointcut' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'AspectJExpressionPointcutAdvisor', desc: { zh: '表达式切点与通知的组装件', en: 'Assembles an expression pointcut with advice' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'TargetSource', desc: { zh: '目标对象来源抽象，代理通过它取到真实对象', en: 'Abstraction from which proxies obtain the target' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'AdvisedSupport', desc: { zh: '持有目标与通知链的配置载体', en: 'Holds the target and advice chain configuration' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'AopProxy', desc: { zh: '代理创建接口', en: 'Proxy creation interface' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'JdkDynamicAopProxy', desc: { zh: 'JDK 动态代理实现，要求目标实现接口', en: 'JDK dynamic proxy, requires an interface' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'Cglib2AopProxy', desc: { zh: 'CGLIB 字节码代理实现，可代理普通类', en: 'CGLIB proxy able to proxy plain classes' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'ProxyFactory', desc: { zh: '统一的代理创建入口，自动选择代理方式', en: 'Unified proxy factory choosing the proxy type' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'ReflectiveMethodInvocation', desc: { zh: '反射式方法调用，串起拦截器链', en: 'Reflective invocation chaining the interceptors' }, chapter: 'aop' },
  { module: I, pkg: 'aop', name: 'DefaultAdvisorAutoProxyCreator', desc: { zh: '自动为匹配的 Bean 创建代理', en: 'Automatically proxies matching beans' }, chapter: 'aop' },

  // ── iteration / core (14) ─────────────────────────────────────────────
  { module: I, pkg: 'core', name: 'Resource', desc: { zh: '资源抽象接口，统一各类资源读取', en: 'Resource abstraction unifying reads' }, chapter: 'bootstrap' },
  { module: I, pkg: 'core', name: 'ClassPathResource', desc: { zh: '类路径资源', en: 'Classpath resource' }, chapter: 'bootstrap' },
  { module: I, pkg: 'core', name: 'FileSystemResource', desc: { zh: '文件系统资源', en: 'Filesystem resource' }, chapter: 'bootstrap' },
  { module: I, pkg: 'core', name: 'UrlResource', desc: { zh: 'URL 资源', en: 'URL resource' }, chapter: 'bootstrap' },
  { module: I, pkg: 'core', name: 'ResourceLoader', desc: { zh: '资源加载器，按路径前缀选择实现', en: 'Resource loader selecting an implementation by prefix' }, chapter: 'bootstrap' },
  { module: I, pkg: 'core', name: 'DefaultResourceLoader', desc: { zh: '默认资源加载器实现', en: 'Default resource loader' }, chapter: 'bootstrap' },
  { module: I, pkg: 'core', name: 'ConversionService', desc: { zh: '类型转换服务接口', en: 'Type-conversion service interface' } },
  { module: I, pkg: 'core', name: 'Converter', desc: { zh: '单一类型转换器', en: 'Single type converter' } },
  { module: I, pkg: 'core', name: 'ConverterFactory', desc: { zh: '转换器工厂，批量产出同族转换器', en: 'Converter factory producing a family of converters' } },
  { module: I, pkg: 'core', name: 'GenericConverter', desc: { zh: '通用转换器，处理复杂类型对', en: 'Generic converter handling complex type pairs' } },
  { module: I, pkg: 'core', name: 'ConverterRegistry', desc: { zh: '转换器注册表', en: 'Converter registry' } },
  { module: I, pkg: 'core', name: 'GenericConversionService', desc: { zh: '通用转换服务实现，维护转换器集合', en: 'Generic conversion service holding converters' } },
  { module: I, pkg: 'core', name: 'DefaultConversionService', desc: { zh: '默认转换服务，注册内置转换器', en: 'Default service with built-in converters' } },
  { module: I, pkg: 'core', name: 'StringToNumberConverterFactory', desc: { zh: '字符串到数字的转换工厂', en: 'String-to-number converter factory' } },

  // ── iteration / stereotype · utils (4) ────────────────────────────────
  { module: I, pkg: 'stereotype', name: 'Component', desc: { zh: '@Component 注解，被扫描器识别为候选组件', en: 'The @Component annotation picked up by the scanner' }, chapter: 'bootstrap' },
  { module: I, pkg: 'utils', name: 'ClassUtils', desc: { zh: '类与反射工具：加载类、判断接口、转换类名', en: 'Class and reflection helpers' } },
  { module: I, pkg: 'utils', name: 'NumberUtils', desc: { zh: '数字解析工具', en: 'Number parsing helpers' } },
  { module: I, pkg: 'utils', name: 'StringValueResolver', desc: { zh: '字符串值解析器，处理占位符后的值', en: 'Resolves string values after placeholder substitution' } },

  // ── original / webmvc (7) ─────────────────────────────────────────────
  { module: O, pkg: 'webmvc', name: 'ApplicationContext', desc: { zh: '极简容器：扫描注解并实例化 Bean', en: 'Minimal container scanning annotations and instantiating beans' }, chapter: 'bootstrap' },
  { module: O, pkg: 'webmvc', name: 'DispatchServlet', desc: { zh: '前端控制器，按 HandlerMapping 分发请求', en: 'Front controller dispatching via HandlerMapping' } },
  { module: O, pkg: 'webmvc', name: 'HandlerMapping', desc: { zh: 'URL 到处理器的映射表', en: 'Maps URLs to handlers' } },
  { module: O, pkg: 'webmvc', name: 'HandlerAdapter', desc: { zh: '处理器适配器，统一调用方式', en: 'Adapter unifying how handlers are invoked' } },
  { module: O, pkg: 'webmvc', name: 'ModelAndView', desc: { zh: '模型与视图的载体', en: 'Carries model data and view name' } },
  { module: O, pkg: 'webmvc', name: 'ViewResolver', desc: { zh: '视图解析器，把逻辑名解析为 View', en: 'Resolves logical view names into View objects' } },
  { module: O, pkg: 'webmvc', name: 'View', desc: { zh: '视图接口，负责渲染输出', en: 'View interface rendering the output' } },

  // ── original / annotation (5) ─────────────────────────────────────────
  { module: O, pkg: 'annotation', name: 'Controller', desc: { zh: '@Controller 注解', en: 'The @Controller annotation' } },
  { module: O, pkg: 'annotation', name: 'Service', desc: { zh: '@Service 注解', en: 'The @Service annotation' } },
  { module: O, pkg: 'annotation', name: 'Autowired', desc: { zh: '@Autowired 注解（极简版）', en: 'The @Autowired annotation (minimal version)' } },
  { module: O, pkg: 'annotation', name: 'RequestMapping', desc: { zh: '@RequestMapping 注解', en: 'The @RequestMapping annotation' } },
  { module: O, pkg: 'annotation', name: 'RequestParam', desc: { zh: '@RequestParam 注解', en: 'The @RequestParam annotation' } },

  // ── original / beans (3) ─────────────────────────────────────────────
  { module: O, pkg: 'beans', name: 'BeanDefinition', desc: { zh: '极简版 Bean 定义', en: 'Minimal bean definition' }, chapter: 'bootstrap' },
  { module: O, pkg: 'beans', name: 'BeanDefinitionReader', desc: { zh: '极简版定义读取器', en: 'Minimal definition reader' }, chapter: 'bootstrap' },
  { module: O, pkg: 'beans', name: 'BeanWrapper', desc: { zh: 'Bean 包装器，承载实例与属性注入', en: 'Wrapper holding the instance and properties' }, chapter: 'lifecycle' },

  // ── original / aop (14) ───────────────────────────────────────────────
  { module: O, pkg: 'aop', name: 'AopConfig', desc: { zh: 'AOP 配置：切点、切面与通知的声明', en: 'AOP config declaring pointcut, aspect and advice' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'AopProxy', desc: { zh: '代理工厂，按条件选择 JDK 或 CGLIB', en: 'Proxy factory choosing JDK or CGLIB' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'DefaultAopProxyFactory', desc: { zh: '默认代理工厂实现', en: 'Default proxy factory' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'JdkDynamicAopProxy', desc: { zh: 'JDK 动态代理', en: 'JDK dynamic proxy' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'CglibAopProxy', desc: { zh: 'CGLIB 代理', en: 'CGLIB proxy' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'AdvisedSupport', desc: { zh: '通知链的配置载体', en: 'Holds the advice chain configuration' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'Advice', desc: { zh: '通知接口', en: 'Advice interface' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'AbstractAspectJAdvice', desc: { zh: 'AspectJ 通知的抽象基类', en: 'Abstract base for AspectJ advice' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'JoinPoint', desc: { zh: '连接点，暴露目标方法与参数', en: 'Join point exposing the target method and args' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'MethodInterceptor', desc: { zh: '方法拦截器接口', en: 'Method interceptor interface' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'MethodInvocation', desc: { zh: '方法调用，承载拦截器链的推进', en: 'Method invocation driving the interceptor chain' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'MethodBeforeAdviceInterceptor', desc: { zh: '前置通知的拦截器包装', en: 'Interceptor wrapping before-advice' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'AfterReturningAdviceInterceptor', desc: { zh: '返回通知的拦截器包装', en: 'Interceptor wrapping after-returning advice' }, chapter: 'aop' },
  { module: O, pkg: 'aop', name: 'AspectJAfterThrowingAdvice', desc: { zh: '异常通知，方法抛错时执行', en: 'Throws-advice executed when the method fails' }, chapter: 'aop' },
]
