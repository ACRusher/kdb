package com.kdb.web.controller;

import com.kdb.manager.ArticleManager;
import com.kdb.model.Articles;
import com.kdb.vo.Article;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-18 下午11:30
 */
@Controller
@RequestMapping("/")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ForumController extends BaseController{

    private final String page="2";

    @Autowired
    private ArticleManager articleManager;

    @RequestMapping(value = "/2")
    public ModelAndView serve(HttpServletRequest request,HttpServletResponse response){
        try {
            return list(request, response, new ModelAndView(page));
        } catch (Exception e) {
            logger.error("",e);
        }
        return new ModelAndView("2");
    }

    private ModelAndView list(HttpServletRequest request,HttpServletResponse response,ModelAndView model){
        String keyword=request.getParameter("keyword");
        model.addObject("keyword",keyword);
        String curIndex=request.getParameter("curIndex");
        String page=request.getParameter("page");
        if(page==null) page="0";
        String pageSize=request.getParameter("pageSize");
        if(pageSize==null) pageSize="5";
        int type=0;
        if(curIndex!=null){
            type=Integer.valueOf(curIndex);
        }
        List<Articles> articles=articleManager.getArticles(Integer.valueOf(page),Integer.valueOf(pageSize),type,keyword);
        List<Article> list=new ArrayList<Article>();
        for(Articles a : articles){
            list.add(a.toVO());
        }
        model.addObject("articles",list);
        return model;
    }
}
