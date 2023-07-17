package com.wychmod.springframework.beans.factory;

/**
 * @description:
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
