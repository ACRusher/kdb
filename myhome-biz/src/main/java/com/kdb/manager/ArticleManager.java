package com.kdb.manager;

import com.kdb.dao.ArticleDAO;
import com.kdb.model.Articles;
import com.kdb.model.User;
import com.kdb.vo.Article;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午10:15
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ArticleManager {

    @Autowired
    private ArticleDAO articleDAO;

    public Articles getById(long id){
        return articleDAO.getById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveArticle(Articles article){
         return articleDAO.addModel(article)==1;
    }

    public boolean saveArticle(String title,String content,User author,int type){
        Articles articles=new Articles();
        articles.setTitle(title);
        articles.setContent(content);
        articles.setAuthorId(author.getId());
        if(StringUtils.isNotBlank(author.getName())){
            articles.setAuthorName(author.getName());
        }else{
            articles.setAuthorName(author.getPhone().substring(0,3)+"****"+author.getPhone().substring(7));
        }
        articles.setType(type);
        articles.setGmtCreate(new Date());
        articles.setGmtModify(new Date());
        return saveArticle(articles);
    }

    public List<Articles> getArticles(int page,int pageSize,int type,String keyword){
        if(page<0) page=0;
        if(pageSize<=0) pageSize=5;
        return articleDAO.getArticles(pageSize, page * pageSize, type, keyword);
    }

    public List<Articles> getRecentArticles(int size){
        return articleDAO.getRecentArticles(size);
    }
}
