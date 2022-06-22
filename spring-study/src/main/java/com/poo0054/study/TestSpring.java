package com.poo0054.study;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/20 14:00
 */
public class TestSpring {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("simpleContext.xml");
		TestBean testBean = classPathXmlApplicationContext.getBean("testBean", TestBean.class);
		testBean.send();
	}
}
