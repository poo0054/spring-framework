package com.poo0054.study.cache;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/4 13:58
 */
public class SpringCacheTest {

    @Test
    void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CacheConfig.class);
        CacheTest cacheTest = applicationContext.getBean(CacheTest.class);
        String s = cacheTest.cacheTest("i like");
        System.out.println(s);
    }

}