package com.poo0054.study.listener;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 10:37
 */
public class SpringListenerTest {

    @Test
    void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("listener/listenerContext.xml");
        ApplicationEventTest applicationEventTest = new ApplicationEventTest("123456");
        Object applicationEventMulticaster = applicationContext.getBean("applicationEventMulticaster");
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster eventMulticaster =
                (SimpleApplicationEventMulticaster)applicationEventMulticaster;
            // eventMulticaster.setErrorHandler(t -> System.out.println("我是spring事件 我出问题了，问题是" +
            // t.getLocalizedMessage()));
        } else if (applicationEventMulticaster instanceof ApplicationEventMulticaster) {
            ApplicationEventMulticaster eventMulticaster = (ApplicationEventMulticaster)applicationEventMulticaster;
        }
        applicationContext.publishEvent(applicationEventTest);
    }

}
