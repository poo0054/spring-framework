package com.poo0054.study.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/4 13:58
 */
public class SpringCacheTest {

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(CacheConfig.class);
        CacheTest cacheTest = applicationContext.getBean(CacheTest.class);
        String s = cacheTest.cacheTest("i like");
        System.out.println(s);
    }

}