package common.aop.annotation;

import java.lang.annotation.*;

/**
 * @author xiliang.zxl
 * @date 2015-10-25 下午6:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface Aop {
}
