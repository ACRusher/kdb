package com.kdb.web.ajax;

import com.kdb.manager.UserManager;
import com.kdb.model.User;
import com.kdb.service.AjaxDispatcherService;
import com.kdb.util.WebUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * ajax 分发器
 *
 * @author xiliang.zxl
 * @date 2016-01-10 下午4:51
 */
@Controller
@RequestMapping("/ajax")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AjaxDispatcher implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Resource
    private UserManager userManager;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public User getLoginUser(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("isonline")){
                    String value=cookie.getValue();
                    String phone=value.split(":")[1];
                    return userManager.getUserByPhone(phone);
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/{method}")
    @ResponseBody
    public Object ajax(@PathVariable("method") String id,HttpServletRequest request){
        AjaxDispatcherService ajaxDispatcherService= (AjaxDispatcherService) applicationContext.getBean("ajaxDispatcherService");
        Map<String,Object> paramMap=request.getParameterMap();
        Map<String,Object> param=new HashMap<String, Object>(paramMap);
        param.put("loginUser",getLoginUser(request));
        return ajaxDispatcherService.service(id,param,request);
    }

    @RequestMapping(value = "/jsonp/{method}")
    public void jsonp(@PathVariable("method")String id,HttpServletRequest request,HttpServletResponse response){
        AjaxDispatcherService ajaxDispatcherService= (AjaxDispatcherService) applicationContext.getBean("ajaxDispatcherService");
        Object data= ajaxDispatcherService.service(id,request.getParameterMap(),request);
        WebUtil.responseJsonp(request.getParameter("callback"),data,response);
        return ;
    }

}
