package common.aop;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.core.Ordered;

/**
 * @author xiliang.zxl
 * @date 2015-10-25 下午4:59
 */
public class SimpleAutoProxyCreator extends AbstractAutoProxyCreator implements Ordered {
    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        String beanClassName=beanClass.getName();
        if(beanClassName.equals("common.aop.EchoBean")){
            System.out.println(beanClassName + " >>>  aop proxy");
            return new Object[]{new MyAdvisor()};
        }
        if(beanName.equals("echoBean")){
            System.out.println(beanClass.getSimpleName());
            System.out.println(EchoBean.class.isAssignableFrom(beanClass));
            return new Object[]{new MyAdvisor()};
        }
        return DO_NOT_PROXY;
    }

    /**
     * 这儿要设置为true 否则多层代理的时候 bean的类型会变为 com.sun.proxy.$Proxy17
     * @return
     */
    @Override
    public boolean isProxyTargetClass() {
        return true;
    }

}
