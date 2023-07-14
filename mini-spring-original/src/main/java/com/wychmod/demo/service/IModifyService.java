package com.wychmod.demo.service;

/**
 * @description: 增删改业务
 * @author: wychmod
 * @date: 2023/6/23
 */
public interface IModifyService {

    /**
     * 增加
     */
    String add(String name, String addr) throws Exception;

    /**
     * 修改
     */
    String edit(Integer id, String name);

    /**
     * 删除
     */
    String remove(Integer id);

}