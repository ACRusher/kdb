package com.kdb.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhouxiliang on 2015/12/19.
 */
@Aspect
@Order(value = 5)
public class MyAspect {
    @Pointcut("execution(* com.kdb.service.SimpleService.*(..) )")
    public void someService(){ }

    @Around("someService()")
    public Object intercept(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println(">>> around aop.");

        try {
            Object source=proceedingJoinPoint.getTarget();
            Object[]args=proceedingJoinPoint.getArgs();
            Signature signature=proceedingJoinPoint.getSignature();
            String methodName=signature.getName();
            Class[] types=new Class[args.length];
            for(int i=0;i<args.length;++i) types[i]=args[i].getClass();
            Method method=source.getClass().getMethod(methodName,types);
//            return method.invoke(source,args);
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "fail";
    }

    @Around("someService()")
    public Object intercept1(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println(">>> around aop.");

        try {
            Object source=proceedingJoinPoint.getTarget();
            Object[]args=proceedingJoinPoint.getArgs();
            Signature signature=proceedingJoinPoint.getSignature();
            String methodName=signature.getName();
            Class[] types=new Class[args.length];
            for(int i=0;i<args.length;++i) types[i]=args[i].getClass();
            Method method=source.getClass().getMethod(methodName,types);
//            return method.invoke(source,args);
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "fail";
    }

    public static void main(String[] args) {
        System.out.println("ss");
    }
}
