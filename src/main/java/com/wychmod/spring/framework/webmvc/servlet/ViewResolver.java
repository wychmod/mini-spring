package com.wychmod.spring.framework.webmvc.servlet;

import java.io.File;

/**
 * @description: 视图转换器，模板引擎
 * @author: wychmod
 * @date: 2023/6/27
 */
public class ViewResolver {
    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";
    private File tempateRootDir;

    public ViewResolver(String tempateRoot) {
        String templateRootPath = this.getClass().getClassLoader().getResource(tempateRoot).getFile();
        this.tempateRootDir = new File(templateRootPath);
    }

    public View resolveViewName(String viewName){
        if (null == viewName || "".equals(viewName.trim())){
            return null;
        }

        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFIX);
        File templateFile = new File((tempateRootDir.getPath() + "/" + viewName).replaceAll("/+","/"));
        return new View(templateFile);
    }
}
