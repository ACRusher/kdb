package com.kdb.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiliang.zxl
 * @date 2016-01-17 下午12:19
 */
@Controller
@RequestMapping("/")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginController extends BaseController {

    private final String page="18";

    @RequestMapping(value = "/18")
    public String serve(HttpServletRequest request,HttpServletResponse response,Model model){
        try {
            return auth(request,response,model);
        } catch (IOException e) {
            logger.error("",e);
        }
        return page;
    }

    private String auth(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
        String phone=request.getParameter("phone");
        String pwd=request.getParameter("pwd");;
        String redirect= request.getParameter("redirect");
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("isonline")){
                    if(StringUtils.isBlank(redirect)){
                        redirect="/home";
                    }
                    response.sendRedirect(redirect);
                }
            }
        }
        if(login(phone,pwd)){
            String value="phone:"+phone;
            Cookie cookie=new Cookie("isonline",value);
            cookie.setDomain("kuandaibang.com");
            cookie.setMaxAge(24*3600);
            response.addCookie(cookie);
            response.sendRedirect(redirect);
        }else{
            //登录失败的提示
            if(StringUtils.isNotBlank(pwd)) {
                model.addAttribute("phone",phone);
                model.addAttribute("pwd",pwd);
                model.addAttribute("message", "登录失败，密码错误！");
            }else {
                //第一次登錄
                model.addAttribute("redirect",redirect);
            }
        }
        return page;
    }
}
