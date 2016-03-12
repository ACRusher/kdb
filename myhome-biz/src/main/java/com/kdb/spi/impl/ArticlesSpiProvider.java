package com.kdb.spi.impl;

import com.kdb.manager.ArticleManager;
import com.kdb.model.Articles;
import com.kdb.spi.AjaxSpi;
import com.kdb.util.Constants;
import com.kdb.util.ParamUtil;
import com.kdb.vo.Article;
import com.kdb.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-01-20 下午11:04
 */
@Service
public class ArticlesSpiProvider implements AjaxSpi<List<Article>>{


    @Autowired
    private ArticleManager articleManager;

    public Result<List<Article>> service(Map<String, Object> param, HttpServletRequest request) {
        Result<List<Article>> result=new Result<List<Article>>(Constants.RESULT_CODE_SUCCESS
                ,Constants.RESULT_MESSAGE_SUCCESS,true,getArticles(param,request));
        return result;
    }

    public List<Article> getArticles(Map<String,Object> param, HttpServletRequest request){
        String page=request.getParameter("page");
        String pageSize=request.getParameter("pageSize");
        String keyword=request.getParameter("keyword");
        String type=request.getParameter("type");
        List<Articles> list=articleManager.getArticles(ParamUtil.getInt(page, 0),
                ParamUtil.getInt(pageSize, 5), ParamUtil.getInt(type, 0), keyword);

        List<Article> result=new ArrayList<Article>();
        for(Articles a : list) {
            result.add(a.toVO());
        }
        return result;
    }
}
