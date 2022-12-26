package com.poo0054.study.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/12/20 11:13
 */
public class ConfigIos {
    public static void main(String[] args) {
        springTest();
    }

    /**
     * spring启动
     */
    public static void springTest() {
        ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("config/simpleContext.xml");
        TestBean testBean = applicationContext.getBean("testBean", TestBean.class);
        testBean.send();
    }
}
