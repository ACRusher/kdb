package zxl.webapp.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiliang.zxl
 * @date 2015-10-24 下午7:18
 */
//struts 会默认使用prototype类型bean
@Namespace("/zxl")
public class HelloAction extends ActionSupport implements ApplicationContextAware {
    private static AtomicInteger integer=new AtomicInteger(0);
    //struts 会尝试设置
    private String name;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(">>>HelloAction initializing....");
    }

    @Action(value = "hello",results = {@Result(name = SUCCESS,location = "/WEB-INF/content/hello.jsp")})
    public String hello(){
        System.out.println(this);
        return SUCCESS;
    }
}
