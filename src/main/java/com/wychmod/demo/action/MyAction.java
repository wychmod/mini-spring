package com.wychmod.demo.action;

import com.wychmod.demo.service.IModifyService;
import com.wychmod.demo.service.IQueryService;
import com.wychmod.spring.framework.annotation.Autowired;
import com.wychmod.spring.framework.annotation.Controller;
import com.wychmod.spring.framework.annotation.RequestMapping;
import com.wychmod.spring.framework.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 接口Url
 * @author: wychmod
 * @date: 2023/6/23
 */
@Controller
@RequestMapping("/web")
public class MyAction {
    
    @Autowired
    IQueryService queryService;
    
    @Autowired
    IModifyService modifyService;

    private void out(HttpServletResponse resp, String str){
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/query.json")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam("name") String name){
        String result = queryService.query(name);
        out(response,result);
    }

    @RequestMapping("/add*.json")
    public void add(HttpServletRequest request,HttpServletResponse response,
                    @RequestParam("name") String name,@RequestParam("addr") String addr){
        String result = modifyService.add(name,addr);
        out(response,result);
    }

    @RequestMapping("/remove.json")
    public void remove(HttpServletRequest request,HttpServletResponse response,
                       @RequestParam("id") Integer id){
        String result = modifyService.remove(id);
        out(response,result);
    }

    @RequestMapping("/edit.json")
    public void edit(HttpServletRequest request,HttpServletResponse response,
                     @RequestParam("id") Integer id,
                     @RequestParam("name") String name){
        String result = modifyService.edit(id,name);
        out(response,result);
    }
    
}
