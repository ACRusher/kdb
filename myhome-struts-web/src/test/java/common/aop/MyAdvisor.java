package common.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.core.Ordered;

/**
 * 请注意，一定不要把业务方的Advisor注入到bean容器，
 * 因为spring会自动搜索Advisor并应用到它认为需要aop的地方（可能不是我们需要的）
 *
 * @author xiliang.zxl
 * @date 2015-10-25 下午3:34
 */
public class MyAdvisor implements Advisor {


    @Override
    public Advice getAdvice() {
        return new MyAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
