package com.kdb;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author xiliang.zxl
 * @date 2016-01-21 上午12:11
 */
@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class BaseTest {

    @Autowired
    protected ApplicationContext applicationContext;

    @Before
    public void init(){
        assert applicationContext!=null;
        System.out.println(">>>> init success.");
    }
}
