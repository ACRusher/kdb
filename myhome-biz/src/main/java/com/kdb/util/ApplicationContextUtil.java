package com.kdb.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author xiliang.zxl
 * @date 2016-01-20 上午12:22
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext_;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext_=applicationContext;

    }

    public static <T> T getBean(String beanName){
        return (T) applicationContext_.getBean(beanName);
    }

}
