package com.kdb.interceptor;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * @author xiliang.zxl
 * @date 2015-12-03 下午11:34
 */
public class ServiceInterceptor1 implements Advice,MethodInterceptor,Ordered {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method=methodInvocation.getMethod();
        Object[] args=methodInvocation.getArguments();
        Object target=methodInvocation.getThis();

        System.out.println(">>> executing method : "+ methodInvocation.getMethod().getName());
        return methodInvocation.proceed();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
