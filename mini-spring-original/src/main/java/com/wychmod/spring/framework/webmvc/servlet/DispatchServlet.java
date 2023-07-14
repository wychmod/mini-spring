package com.wychmod.spring.framework.webmvc.servlet;

import com.wychmod.spring.framework.webmvc.servlet.context.ApplicationContext;
import com.wychmod.spring.framework.annotation.Controller;
import com.wychmod.spring.framework.annotation.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 委派模式：负责任务调度，请求分发
 * @author: wychmod
 * @date: 2023/6/23
 */
public class DispatchServlet extends HttpServlet {

    /**
     * Servlet init()方法中创建ApplicationContext
     */
    private ApplicationContext applicationContext;

    /**
     * 保存所有的Url和方法的映射关系
     */
    private List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    /**
     * 当找到对应handlerMapping，通过HandlerAdapter的handle进行参数适配反射实现method
     */
    private Map<HandlerMapping, HandlerAdapter> handlerAdapters = new HashMap<HandlerMapping, HandlerAdapter>();

    /**
     * 模板引擎
     */
    private List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 初始化Spring核心IoC容器、 完成了IoC、DI
        applicationContext = new ApplicationContext(config.getInitParameter("contextConfigLocation"));

        //===========MVC部分===========

        // 初始化九大组件
        initStrategies(applicationContext);

        System.out.println("Mini-Spring framework is init.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 6. 委派，根据URL去找到一个对应的Method并通过response返回
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            try {
                processDispatchResult(req, resp, new ModelAndView("500"));
            } catch (Exception e1) {
                e1.printStackTrace();
                resp.getWriter().write("500 Exception, Detail: " + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    private void initStrategies(ApplicationContext context) {
//        多文件上传的组件
//        initMultipartResolver(context);
//        初始化本地语言环境
//        initLocaleResolver(context);
//        初始化模板处理器
//        initThemeResolver(context);
//        初始化Url路径和方法映射
        initHandlerMappings(context);
//        初始化参数适配器
        initHandlerAdapters(context);
//        初始化异常拦截器
//        initHandlerExceptionResolvers(context);
//        初始化视图预处理器
//        initRequestToViewNameTranslator(context);
//        初始化视图转换器
        initViewResolvers(context);
//        FlashMap管理器
//        initFlashMapManager(context);

    }

    /**
     * 初始化url和Method的一对一对应关系
     */
    private void initHandlerMappings(ApplicationContext context) {
        if (context.getBeanDefinitionCount() == 0) {
            return;
        }

        for (String beanName : this.applicationContext.getBeanDefinitionNames()) {
            Object instance = applicationContext.getBean(beanName);
            Class<?> clazz = instance.getClass();

            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }

            // 获取保存写在类上面的@RequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }

            // 获取所有的public方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }

                // 提取每个方法上面配置的url
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handlerMappings.add(new HandlerMapping(pattern, method, instance));
                System.out.println("Mapped : " + regex + "," + method);
            }
        }
    }

    private void initHandlerAdapters(ApplicationContext context) {
        for (HandlerMapping handlerMapping : handlerMappings) {
            this.handlerAdapters.put(handlerMapping, new HandlerAdapter());
        }
    }

    private void initViewResolvers(ApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File file : templateRootDir.listFiles()) {
            this.viewResolvers.add(new ViewResolver(templateRoot));
        }
    }


    /**
     * 进行委派
     *
     * @param req
     * @param resp
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //完成了对HandlerMapping的封装
        //完成了对方法返回值的封装ModelAndView

        // 1. 通过URL获得一个HandlerMapping
        HandlerMapping handler = getHandler(req);
        if (handler == null) {
            processDispatchResult(req, resp, new ModelAndView("404"));
            return;
        }

        // 2. 根据一个HandlerMaping获得一个HandlerAdapter
        HandlerAdapter ha = getHandlerAdapter(handler);

        // 3. 解析某一个方法的形参和返回值之后，统一封装为ModelAndView对象, (这个时候上层程序员写的逻辑应该已经结束)
        ModelAndView modelAndView = ha.handler(req, resp, handler);

        // 把ModelAndView变成一个ViewResolver，并进行渲染
        processDispatchResult(req, resp, modelAndView);

    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {return;}
        if (this.viewResolvers.isEmpty()) {return;}

        for (ViewResolver viewResolver : this.viewResolvers) {
            View view = viewResolver.resolveViewName(modelAndView.getViewName());
            // 往浏览器进行渲染
            view.render(modelAndView.getModel(), req, resp);
            return;
        }
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        return this.handlerAdapters.get(handler);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()) {
            return null;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        for (HandlerMapping mapping : handlerMappings) {
            Matcher matcher = mapping.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return mapping;
        }
        return null;
    }

}
