package common.aop;

import org.apache.struts2.interceptor.ApplicationAware;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 综合来比较，通过注解配置的方式完全覆盖老的配置方式，并且更加的灵活和丰富，推荐通过注解配置方式使用AOP
 *
 * @author xiliang.zxl
 * @date 2015-10-25 下午3:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class AopTest {

    @Resource
    private EchoBean echoBean;

    /**
     * 使用实现 Advice、Advisor 以及 AutoProxyCreator 的方式 来AOP截断
     */
    @Test
    public void testAOP(){
        Assert.assertNotNull(echoBean);
        echoBean.echo("hello");
    }

    /**
     * 使用注解的方式来做 aop 代理，注意两种aop截断会同时起作用
     */
    @Test
    public void testAnnotaionAOP(){
        Assert.assertNotNull(echoBean);
        echoBean.say("hello");
        echoBean.sing("hello");
        echoBean.search();
        echoBean.drink();
        echoBean.argFunc(null,0,true);
    }

}
