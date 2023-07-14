package wychmod.demo.action;

import com.wychmod.demo.service.IQueryService;
import com.wychmod.spring.framework.annotation.Autowired;
import com.wychmod.spring.framework.annotation.Controller;
import com.wychmod.spring.framework.annotation.RequestMapping;
import com.wychmod.spring.framework.annotation.RequestParam;
import com.wychmod.spring.framework.webmvc.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: mvc模板url接口
 * @author: wychmod
 * @date: 2023/6/26
 */
@Controller
@RequestMapping("/")
public class PageAction {

    @Autowired
    IQueryService queryService;

    @RequestMapping("/first.html")
    public ModelAndView query(@RequestParam("spring") String spring){
        String result = queryService.query(spring);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("spring", spring);
        model.put("data", result);
        model.put("token", "12345");
        return new ModelAndView("first.html", model);
    }
}
