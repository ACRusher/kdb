package com.kdb.web.controller;

import com.kdb.manager.ArticleManager;
import com.kdb.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiliang.zxl
 * @date 2016-01-17 下午4:21
 */
@Controller
@RequestMapping("/")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PostArticleController extends BaseController {

    private final String page = "3";

    @Autowired
    private ArticleManager articleManager;

    @RequestMapping(value = "/3")
    public String serve(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            return doBiz(request, response, model);
        } catch (Exception e) {
            logger.error("", e);
        }
        return page;
    }

    private String doBiz(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        User user = getLoginUser();
        if (user == null) {
            response.sendRedirect("/18");
            return null;
//            return "redirect:/18";
        }
        //处理表单提交
        String tile = request.getParameter("title");
        String content = request.getParameter("content");
        //来源页面
        String redirect = request.getParameter("redirect");
        model.addAttribute("redirect", redirect);
        //要发贴的类型
        String type = request.getParameter("type");
        model.addAttribute("curIndex", type);
        //保存帖子
        if (StringUtils.isNotBlank(tile) && StringUtils.isNotBlank(content)) {
            if (articleManager.saveArticle(tile, content, user, Integer.valueOf(type))) {
                if(redirect==null) redirect="/home";
                response.sendRedirect(redirect+"?curIndex="+type);
                return null;
                //保存成功后重定向
            }
        }
        if (StringUtils.isNotBlank(tile) || StringUtils.isNotBlank(content)){
            model.addAttribute("message","保存失败!");
        }
        return page;


    }
}
