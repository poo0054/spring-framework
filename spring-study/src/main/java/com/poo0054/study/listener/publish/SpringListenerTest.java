package com.poo0054.study.listener.publish;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 10:37
 */
public class SpringListenerTest {

    @Test
    void test() {
        ConfigurableApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("listener/publish/listenerContext.xml");
        applicationContext.addApplicationListener(new ApplicationListenerTest());
        applicationContext.publishEvent(new ApplicationEventTest("我是 ApplicationEventTest"));
    }

}
