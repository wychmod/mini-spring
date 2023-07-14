package wychmod.demo.service.impl;

import com.wychmod.demo.service.IQueryService;
import com.wychmod.spring.framework.annotation.Service;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 查询业务
 * @author: wychmod
 * @date: 2023/6/23
 */
@Service
@Slf4j
public class QueryService implements IQueryService {

    @Override
    public String query(String name) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
        log.info("这是在业务方法中打印的：" + json);
        return json;
    }
}
