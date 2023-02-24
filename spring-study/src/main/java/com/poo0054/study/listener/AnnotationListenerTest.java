package com.poo0054.study.listener;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.config.ContextNamespaceHandler;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 11:56
 */
public class AnnotationListenerTest {

    /**
     * 在spring中，需要注入{@link org.springframework.context.event.EventListenerMethodProcessor}和{@link org.springframework.context.event.DefaultEventListenerFactory}
     * <p>
     * 这俩个类 如果添加<context:annotation-config/>作用也是一样的，因为： {@link ContextNamespaceHandler#init()}方法中初始化
     * {@link org.springframework.context.annotation.AnnotationConfigBeanDefinitionParser}
     * {@link org.springframework.context.event.EventListenerMethodProcessor#postProcessBeanFactory(ConfigurableListableBeanFactory)}中添加{@link EventListenerFactory}找到
     * DefaultEventListenerFactory，
     * 在{@link EventListenerMethodProcessor#afterSingletonsInstantiated()}中使用DefaultEventListenerFactory创建加了注解的方法并加入（addApplicationListener）多播监听器
     *
     * @param event 事件
     */
    @EventListener
    public void onApplicationEvent(ApplicationEventTest event) {
        System.out.println("------------------AnnotationListenerTest-------------------" + event);
    }
}
