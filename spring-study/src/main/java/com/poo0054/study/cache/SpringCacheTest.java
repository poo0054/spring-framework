package com.poo0054.study.cache;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/4 13:58
 */
public class SpringCacheTest {

    private static AnnotationConfigApplicationContext annotationConfigApplicationContext;

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
    }

}