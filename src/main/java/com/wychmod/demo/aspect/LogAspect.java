package com.wychmod.demo.aspect;

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
    public void before() {
        //这个方法中的逻辑，是由我们自己写的
        log.info("Invoker Before Method!!!");
    }

    /**
     * 在调用一个方法之后，执行after方法
     */
    public void after(){
        log.info("Invoker After Method!!!");
    }

    /**
     * 抛出异常执行
     */
    public void afterThrowing(){

        log.info("出现异常");
    }
}
