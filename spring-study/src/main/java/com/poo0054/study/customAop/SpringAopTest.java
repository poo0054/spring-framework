package com.poo0054.study.customAop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.poo0054.study.customAop.config.AopConfig;

/**
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/4 13:58
 */
public class SpringAopTest {

    @Test
    void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        AopTest cacheTest = applicationContext.getBean(AopTest.class);
        String s = cacheTest.aop();
        System.out.println(s);
    }

}