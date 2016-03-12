package com.kdb.common.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhouxiliang on 2016/1/25.
 */
public class AppConfig {

    public static Properties properties=new Properties();
    static {
        PathMatchingResourcePatternResolver patternResolver=new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources=patternResolver.getResources("classpath:*.properties");
            for(Resource resource:resources){
                Properties p=new Properties();
                p.load(resource.getInputStream());
                for(Object obj : p.keySet()){
                    properties.put(obj,p.get(obj));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String name){
        return properties.getProperty(name);
    }

    public static void main(String[] args) {
        System.out.println(AppConfig.getProperty("name"));
    }
}
