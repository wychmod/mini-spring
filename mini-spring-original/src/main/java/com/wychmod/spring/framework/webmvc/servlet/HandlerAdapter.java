package com.wychmod.spring.framework.webmvc.servlet;

import com.wychmod.spring.framework.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 动态参数适配器
 * @author: wychmod
 * @date: 2023/6/27
 */
public class HandlerAdapter {

    public ModelAndView handler(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handlerMapping) throws Exception {
        // 保存形参列表， 将参数名称和位置保存
        Map<String, Integer> paramIndexMapping = new HashMap<String, Integer>();

        // 提取方法中加了注解的参数, 通过运行时的状态去拿到参数
        Annotation[][] parameterAnnotations = handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof RequestParam) {
                    String paramName = ((RequestParam) annotation).value();
                    if (!"".equals(paramName.trim())) {
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }

        // 上面只是装入了有注解的参数，现在装入resp 和 req的位置
        Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == HttpServletRequest.class || parameterType == HttpServletResponse.class) {
                paramIndexMapping.put(parameterType.getName(), i);
            }
        }

        // http://localhost/web/query?name=Tom&Cat 获得URL后面的参数
        Map<String, String[]> parameterMap = req.getParameterMap();

        Object[] paramValues = new Object[parameterTypes.length];

        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            String value = Arrays.toString(parameterMap.get(param.getKey()))
                    .replaceAll("\\[|\\]", "")
                    .replaceAll("\\s+", ",");

            if (!paramIndexMapping.containsKey(param.getKey())) {
                continue;
            }

            int index = paramIndexMapping.get(param.getKey());

            // //允许自定义的类型转换器Converter
            paramValues[index] = castStringValue(value, parameterTypes[index]);
        }

        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())){
            int index = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[index] = req;
        }

        if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())){
            int index = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[index] = resp;
        }

        // 反射实现该方法
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);
        if (result == null || result instanceof Void) {
            return null;
        }
        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == ModelAndView.class;
        if(isModelAndView){
            return (ModelAndView)result;
        }
        return null;
    }

    /**
     * 暂时硬编码实现
     * @param value
     * @param parameterType
     * @return
     */
    private Object castStringValue(String value, Class<?> parameterType) {
        if (String.class == parameterType) {
            return value;
        } else if (Integer.class == parameterType) {
            return Integer.valueOf(value);
        } else if (Double.class == parameterType) {
            return Double.valueOf(value);
        } else {
            if (value != null) {
                return value;
            }
            return null;
        }
    }

}
