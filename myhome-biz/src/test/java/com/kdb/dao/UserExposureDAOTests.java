package com.kdb.dao;

import com.kdb.BaseTest;
import com.kdb.model.UserExposure;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午8:55
 */
public class UserExposureDAOTests extends BaseTest {

    @Autowired
    private UserExposureDAO userExposureDAO;

    @Test
    public void testInsert(){
        UserExposure userExposure=new UserExposure();
        userExposure.setContent("content");
        userExposure.setGmtCreate(new Date());
        userExposure.setGmtModify(new Date());
        userExposure.setOperatorId(1L);
        userExposure.setTitle("title");
        userExposure.setUserId(1L);
        userExposure.setUserIdentityId("111");
        userExposure.setUserName("name");
        userExposure.setUserPhone("13812312313");
        userExposureDAO.addModel(userExposure);
        assert userExposure.getId()!=null;
        System.out.println(userExposure.getId());
    }
}
