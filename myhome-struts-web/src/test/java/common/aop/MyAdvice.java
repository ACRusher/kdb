package common.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

/**
 * @author xiliang.zxl
 * @date 2015-10-25 下午3:34
 */
public class MyAdvice implements Advice ,MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String methodName=methodInvocation.getMethod().getName();
        System.out.println( AopUtils.isAopProxy(methodInvocation.getThis()));
        System.out.println(">>> aop autoProxy interceptor | class="+methodInvocation.getThis().getClass().getSimpleName()+"  method="+methodName);
        return methodInvocation.proceed();
    }
}
