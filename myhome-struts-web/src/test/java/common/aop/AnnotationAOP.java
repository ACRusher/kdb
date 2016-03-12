package common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * 关于aspectj 配置aop表达式的细节较多，详情参见这个文章 {@see http://sishuok.com/forum/posts/list/281.html}
 *
 * @author xiliang.zxl
 * @date 2015-10-25 下午4:04
 */
@Aspect
@Order(value = Integer.MAX_VALUE)
public class AnnotationAOP {


    @Pointcut("execution(* common.aop.*.say(..)) || execution(* common.test..*(..))")
    public void pointCutDemo1() {
        //按照指定的正则表达式去匹配 ..可以用来跨越多个包以及类名、..可以代表多个入参 *代表多个字符
    }

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void pointCutDemo2() {
        //如果一个方法有指定的注解，则拦截
    }

    @Pointcut("@within(common.aop.annotation.Aop)")
    public void pointCutDemo3() {
        //如果一个类 或者 一个方法，在指定的Aop注解范围内，则拦截
    }

    @Pointcut("within(common.aop..*)")
    public void pointCutDemo4() {
        //在指定的包内，则拦截
    }

    @Pointcut("args(String,int,boolean)")
    public void pointCutDemo5() {
        //如果一个类 或者 一个方法，在指定的注解范围内，则拦截
    }


    @Around("pointCutDemo1() || pointCutDemo2() || pointCutDemo3() || pointCutDemo4() || pointCutDemo5()")
    public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println(">>>> aop annotation interceptor | " + proceedingJoinPoint.getSignature().getName());
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
