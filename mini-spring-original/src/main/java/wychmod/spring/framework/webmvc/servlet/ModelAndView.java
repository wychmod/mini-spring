package wychmod.spring.framework.webmvc.servlet;

import java.util.Map;

/**
 * @description: ModelAndView包含模型数据和视图信息。给DispatcherServlet，再传递给ViewResolver进行视图解析和渲染。
 * @author: wychmod
 * @date: 2023/6/26
 */
public class ModelAndView {
    /**
     * 视图名，例如xxx.html
     */
    private String viewName;

    /**
     * 模型数据
     */
    private Map<String, ?> model;

    public ModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}
