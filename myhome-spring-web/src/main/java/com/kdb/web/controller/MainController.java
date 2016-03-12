package com.kdb.web.controller;

import com.kdb.service.SimpleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2015-12-03 下午11:42
 */
@Controller
@RequestMapping("/")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MainController extends BaseController {

    @Autowired
    private SimpleService simpleService;

//    @RequestMapping(value = "/info" , method = {RequestMethod.GET})
//    @ResponseBody
//    private Map getInfo(@RequestParam(required = false) String name,HttpServletRequest request){
//        Map map=new HashMap();
//        map.put("a", 1);
//        String result=simpleService.doNothing("success");
//        return map;
//    }

    @RequestMapping(value = "/{id}")
    public String serve(@PathVariable("id") String id,HttpServletRequest request,HttpServletResponse response,Model model){
        try {
            return route(id,request,response,model);
        } catch (IOException e) {
            logger.error("",e);
        }
        return id;
    }

    private String route(String id,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {

        return id;
    }

}
