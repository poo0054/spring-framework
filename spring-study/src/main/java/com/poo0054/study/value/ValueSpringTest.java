package com.poo0054.study.value;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhangzhi
 * @date 2023/2/20
 */
public class ValueSpringTest {
    @Test
    void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ValueConfiguration.class);
        ValueTestBean valueTestBean = applicationContext.getBean("valueTestBean", ValueTestBean.class);
        valueTestBean.getName();
        System.out.println("valueTestBean---------------" + valueTestBean.getName());
    }
}
