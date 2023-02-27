package com.poo0054.study.listener.event;

import org.springframework.context.ApplicationListener;

/**
 * 监听器测试
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 10:35
 */
public class ListenerTest implements ApplicationListener<EventTest> {

    @Override
    public void onApplicationEvent(EventTest event) {
        System.out.println("------------------ApplicationListenerTest-------------------" + event);
    }
}
