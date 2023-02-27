package com.poo0054.study.listener.publish;

import org.springframework.context.ApplicationListener;

/**
 * @author zhangzhi
 * @date 2023/2/27
 */
public class ApplicationListenerTest implements ApplicationListener<ApplicationEventTest> {
    @Override
    public void onApplicationEvent(ApplicationEventTest event) {
        System.out.println("发布事件 " + event.getData());
    }
}
