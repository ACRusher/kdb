package com.kdb.ehcache;

import com.kdb.vo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhouxiliang on 2015/12/18.
 */
@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EhCacheTests {

    @Resource
    private BizManager bizManager;

    @Test
    public void testEhcache(){
        for(int i=0;i<100;++i){
            System.out.println(bizManager.uuid("zxl"+i%10));
        }
    }

    @Test
    public void testMysql(){
        List<Person> list=bizManager.getAllPerson();
        assert list.size()>=1;
        System.out.println(list.get(0));
    }

}
