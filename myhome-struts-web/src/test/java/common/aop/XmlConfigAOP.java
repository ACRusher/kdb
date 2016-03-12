package common.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by zhouxiliang on 2016/1/26.
 */
public class XmlConfigAOP {
    public void beforeDomain() {
        System.out.println("This is beforeDomain....");
    }

    public void afterDomain() {
        System.out.println("This is afterDomain....");
    }

    public void afterReturning() {
        System.out.println("This is afterReturning....");
    }

    public void afterThrowing() {
        System.out.println("This is afterThrowing....");
    }

    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("===========around before advice");
        Object retVal = pjp.proceed(new Object[] {"【环绕通知】"});
        //Object retVal = pjp.proceed();
        System.out.println("===========around after advice");
        return retVal;
    }
}
