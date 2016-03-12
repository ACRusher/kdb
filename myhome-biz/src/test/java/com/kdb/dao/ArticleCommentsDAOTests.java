package com.kdb.dao;

import com.kdb.BaseTest;
import com.kdb.model.ArticleComments;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午3:07
 */
public class ArticleCommentsDAOTests extends BaseTest {

    @Autowired
    private ArticleCommentsDAO articleCommentsDAO;

    @Test
    public void testInsert(){
        ArticleComments articleComments=new ArticleComments();
        articleComments.setContent("楼主说的是真的吗？");
        articleComments.setType(1);
        articleComments.setAuthorName("那棵槐树");
        articleComments.setAuthorId(1L);
        articleComments.setArticleId(1L);
        articleComments.setSequence(1);
        articleComments.setToAuthorId(1L);
        articleComments.setToAuthorName("那棵槐树");
        articleComments.setToSequence(1);
        articleComments.setGmtCreate(new Date());
        articleComments.setGmtModify(new Date());
        articleCommentsDAO.addModel(articleComments);
    }

    @Test
    public void testSelect(){
        System.out.println(articleCommentsDAO.getById(2L));;
    }

    @Test
    public void testUpdate(){
        ArticleComments articleComments=articleCommentsDAO.getById(2L);
        articleComments.setContent("楼主说的是真的吗??");
        articleCommentsDAO.updateById(articleComments);
    }

    @Test
    public void testDelete(){
        articleCommentsDAO.deleteById(2L);
    }
}
