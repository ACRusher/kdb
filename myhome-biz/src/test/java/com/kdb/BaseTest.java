package com.kdb;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-13 下午10:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-common.xml")
@TransactionConfiguration(defaultRollback = false)
public class BaseTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    protected void init() {
        assert applicationContext != null;
    }

    @Test
    public void testSpringContext() {

    }
}
