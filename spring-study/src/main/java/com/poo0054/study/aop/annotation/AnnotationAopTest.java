package com.poo0054.study.aop.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author poo00
 */
public class AnnotationAopTest {
    @Test
    void test() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
            new AnnotationConfigApplicationContext(AopConfig.class);
        SimpleService simpleService = annotationConfigApplicationContext.getBean("simpleService", SimpleService.class);
        String send = simpleService.send();
        System.out.println(send);
    }
}
