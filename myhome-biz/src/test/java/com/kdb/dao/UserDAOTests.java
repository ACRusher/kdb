package com.kdb.dao;

import com.kdb.BaseTest;
import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.ArticlesMapper;
import com.kdb.manager.ArticleManager;
import com.kdb.model.Articles;
import com.kdb.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-13 下午11:00
 */
public class UserDAOTests extends BaseTest {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ArticlesMapper articlesMapper;

    @Test
    public void testTmp(){
        System.out.println(getArticles(5,0,2));
    }
    public List<Articles> getArticles(int limit,int offset,int type){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper=Where.andList();
        wrapper.add(ColumnFilter.instance().column("type").value(type).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        paramMap.limit(offset,limit);
        return articlesMapper.getByCondition(paramMap.build());
    }

    @Test
    public void testInsert(){
        User user=new User();
        user.setGmtCreate(new Date());
        user.setGmtModify(new Date());
        user.setName("测试小A");
        user.setAddress("北京");
        user.setAge(20);
        user.setNick("小A");
        user.setPassword("123");
        user.setHeadPicture("/default.jpg");
        user.setIdentityId("123");
        user.setPhone("13812341234");
        user.setVillageId(1L);
        user.setOperatorId(1L);
        userDAO.addModel(user);
    }

    @Test
    public void testUpdate(){
        User user=userDAO.getById(2L);
        user.setName("测试小A");
        user.setAddress("北京市");
        user.setNick("小A");
        userDAO.updateById(user);
    }

    @Test
    public void testDelete(){
        userDAO.deleteById(2L);
    }

    @Test
    public void testSelectById(){
        User user=userDAO.getById(1L);
        assert user!=null;
        System.out.println(user);
    }

}
