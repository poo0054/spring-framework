package com.poo0054.study.listener;

import org.springframework.context.ApplicationListener;

/**
 * 监听器测试
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 10:35
 */
public class ApplicationListenerTest implements ApplicationListener<ApplicationEventTest> {

    @Override
    public void onApplicationEvent(ApplicationEventTest event) {
        System.out.println("------------------ApplicationListenerTest-------------------" + event);
    }
}
