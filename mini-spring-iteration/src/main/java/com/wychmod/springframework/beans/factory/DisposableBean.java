package com.wychmod.springframework.beans.factory;

/**
 * @description: 销毁时释放资源的 bean 实现的接口， 在释放时调用destroy()
 * @author: wychmod
 * @date: 2023/7/17
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
