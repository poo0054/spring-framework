package com.poo0054.study.ioc.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/20 14:00
 */
public class SpringIocTest {

    /**
     * spring启动
     */
    @Test
    void springTest() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/simpleContext.xml");
        TestBean testBean = applicationContext.getBean("testBean", TestBean.class);
        System.out.println("testBean---------------" + testBean);
    }

}
