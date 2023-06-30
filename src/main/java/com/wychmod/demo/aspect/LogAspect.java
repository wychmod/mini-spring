package com.wychmod.demo.aspect;

import com.wychmod.spring.framework.aop.aspect.JoinPoint;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: Aop测试类
 * @author: wychmod
 * @date: 2023/6/28
 */
@Slf4j
public class LogAspect {

    /**
     * 在调用一个方法之前，执行before方法
     */
    public void before(JoinPoint joinPoint) {
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(), System.currentTimeMillis());
        //这个方法中的逻辑，是由我们自己写的
        log.info("Invoker Before Method!!!");
    }

    /**
     * 在调用一个方法之后，执行after方法
     */
    public void after(JoinPoint joinPoint){
        long startTime = (Long)joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
        long endTime = System.currentTimeMillis();

        log.info("Invoker After Method!!! use time " + (endTime - startTime));
    }

    /**
     * 抛出异常执行
     */
    public void afterThrowing(JoinPoint joinPoint,Throwable ex){

        log.info("出现异常" + ex.getMessage());
    }
}
