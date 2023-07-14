package com.wychmod.spring.framework.aop.config;

import lombok.Data;

/**
 * @description: Aop切面配置类
 * @author: wychmod
 * @date: 2023/6/28
 */
@Data
public class AopConfig {
    private String pointCut;
    private String aspectClass;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;

}
