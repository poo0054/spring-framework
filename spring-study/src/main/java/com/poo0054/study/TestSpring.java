package com.poo0054.study;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticMessageSource;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/20 14:00
 */
public class TestSpring {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("simpleContext.xml");
		StaticMessageSource yourMessageSource = classPathXmlApplicationContext.getBean("yourMessageSource", StaticMessageSource.class);
		System.out.println(yourMessageSource);
	}
}
