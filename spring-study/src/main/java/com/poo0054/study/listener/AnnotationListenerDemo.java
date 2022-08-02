package com.poo0054.study.listener;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.config.ContextNamespaceHandler;
import org.springframework.context.event.EventListener;
import org.w3c.dom.Element;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 11:56
 */
public class AnnotationListenerDemo {

	/**
	 * 在spring中，需要注入{@link org.springframework.context.event.EventListenerMethodProcessor}和{@link org.springframework.context.event.DefaultEventListenerFactory}
	 * <p>
	 * 这俩个类 如果添加<context:annotation-config/>作用也是一样的
	 * 原理：
	 * ---> {@link ContextNamespaceHandler#init()}方法中初始化 AnnotationConfigBeanDefinitionParser
	 * {@link org.springframework.context.annotation.AnnotationConfigBeanDefinitionParser#parse(Element, ParserContext)}中调用了{@link org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry)}
	 * 方法，里面注入了{@link org.springframework.context.event.EventListenerMethodProcessor}和{@link org.springframework.context.event.DefaultEventListenerFactory}俩个类
	 * <p>
	 * 在springboot中，也是这样使用的，在创建createApplicationContext中调用{@link org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry)}
	 *
	 * @param event 事件
	 */
	@EventListener
	public void onApplicationEvent(ApplicationEventDemo event) {
		System.out.println("------------------DemoListener-------------------" + event);
	}
}
