package com.poo0054.study.listener.event;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 10:37
 */
public class SpringEventTest {

    @Test
    void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("listener/event/EventContext.xml");
        applicationContext.publishEvent(new EventTest("123456"));
    }

}
