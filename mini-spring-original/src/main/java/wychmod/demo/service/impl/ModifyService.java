package wychmod.demo.service.impl;

import com.wychmod.demo.service.IModifyService;

/**
 * @description: 增删改业务
 * @author: wychmod
 * @date: 2023/6/23
 */
public class ModifyService implements IModifyService {
    @Override
    public String add(String name, String addr) throws Exception {
        throw new Exception("测试抛出来的异常");
//        return "modifyService add,name=" + name + ",addr=" + addr;
    }

    @Override
    public String edit(Integer id, String name) {
        return "modifyService edit,id=" + id + ",name=" + name;
    }

    @Override
    public String remove(Integer id) {
        return "modifyService id=" + id;
    }
}
