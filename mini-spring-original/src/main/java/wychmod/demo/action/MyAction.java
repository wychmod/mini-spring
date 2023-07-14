package wychmod.demo.action;

import com.wychmod.demo.service.IModifyService;
import com.wychmod.demo.service.IQueryService;
import com.wychmod.spring.framework.annotation.Autowired;
import com.wychmod.spring.framework.annotation.Controller;
import com.wychmod.spring.framework.annotation.RequestMapping;
import com.wychmod.spring.framework.annotation.RequestParam;
import com.wychmod.spring.framework.webmvc.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 接口Url
 * @author: wychmod
 * @date: 2023/6/23
 */
@Controller
@RequestMapping("/web")
public class MyAction {

    @Autowired IQueryService queryService;
    @Autowired IModifyService modifyService;

    @RequestMapping("/query.json")
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam("name") String name){
        String result = queryService.query(name);
        return out(response,result);
    }

    @RequestMapping("/add*.json")
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response,
                              @RequestParam("name") String name,@RequestParam("addr") String addr){
        try {
            String result = modifyService.add(name, addr);
            return out(response,result);
        }catch (Throwable e){
            Map<String,String> model = new HashMap<String,String>();
            model.put("detail",e.getCause().getMessage());
            model.put("stackTrace", Arrays.toString(e.getStackTrace()));
            return new ModelAndView("500",model);
        }
    }

    @RequestMapping("/remove.json")
    public ModelAndView remove(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam("id") Integer id){
        String result = modifyService.remove(id);
        return out(response,result);
    }

    @RequestMapping("/edit.json")
    public ModelAndView edit(HttpServletRequest request,HttpServletResponse response,
                               @RequestParam("id") Integer id,
                               @RequestParam("name") String name){
        String result = modifyService.edit(id,name);
        return out(response,result);
    }



    private ModelAndView out(HttpServletResponse resp,String str){
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

