package com.poo0054.study.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/12/20 11:13
 */
public class ConfigIocTest {

    /**
     * spring启动
     */
    @Test
    public static void springTest() {
        ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("config/simpleContext.xml");
        TestBean testBean = applicationContext.getBean("testBean", TestBean.class);
        testBean.send();
    }
}
