package com.kdb.web.controller;

import com.kdb.dao.mapper.UserMapper;
import com.kdb.manager.UserManager;
import com.kdb.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author xiliang.zxl
 * @date 2016-01-17 下午12:20
 */
public class BaseController implements ApplicationContextAware {
    protected static final Logger logger= LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserManager userManager;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public  HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public User getLoginUser(){
        Cookie[] cookies=getRequest().getCookies();
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

    protected boolean login(String phone,String pwd){
        if(StringUtils.isBlank(phone) || StringUtils.isBlank(pwd) ) {
            return false;
        }
        if(!NumberUtils.isNumber(phone) || phone.length()!=11){
            return false;
        }
        User user=userManager.getUserByPhone(phone);
        if(user!=null){
            if(user.getPassword().equals(pwd)){
                return true;
            }
            return false;
        }
        user=new User();
        user.setPhone(phone);
        user.setPassword(pwd);
        user.setNick(phone.substring(0, 3) + "****" + phone.substring(7));
        user.setHeadPicture("default.jpg");
        user.setGmtCreate(new Date());
        user.setGmtModify(new Date());
        return userManager.saveUser(user);
    }

}
