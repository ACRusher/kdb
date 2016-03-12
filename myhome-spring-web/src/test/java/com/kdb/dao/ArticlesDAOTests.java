package com.kdb.dao;

import com.kdb.BaseTest;
import com.kdb.model.Articles;
import com.kdb.vo.Article;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;

/**
 * @author xiliang.zxl
 * @date 2016-01-21 上午12:13
 */
@ContextConfiguration("classpath:spring-context.xml")
@TransactionConfiguration(defaultRollback = false)
public class ArticlesDAOTests extends BaseTest {

    @Autowired
    private ArticleDAO articleDAO;

    public void init(){
        super.init();
    }

    @Test
    public void testInsert(){
        Articles article=new Articles();
        article.setGmtCreate(new Date());
        article.setGmtModify(new Date());
        article.setAuthorId(1L);
        article.setTitle("如何选择宽带？");
        article.setContent("怎么选择物美价廉的宽带呢");
        article.setPictures("default.jpg");
        article.setType(1);
        articleDAO.addModel(article);
    }

    @Test
    public void testUpdate(){
        Articles article=articleDAO.getById(2L);
        article.setContent("大家有什么好建议吗？");
        articleDAO.updateById(article);
    }

    @Test
    public void testDelete(){
        articleDAO.deleteById(1L);
    }
}
