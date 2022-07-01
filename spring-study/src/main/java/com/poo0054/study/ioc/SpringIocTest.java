package com.poo0054.study.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/20 14:00
 */
public class SpringIocTest {

	public static void main(String[] args) {
		springTest();
	}

	public static void springTest() {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/simpleContext.xml");
//		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("SpringAopContext.xml");
		TestBean testBean = applicationContext.getBean("testBean", TestBean.class);
//		TestBean1 testBean1 = applicationContext.getBean("testBean1", TestBean1.class);
		System.out.println("testBean---------------" + testBean);
//		System.out.println("testBean1---------------" + testBean.getBean1() != null ? testBean.getBean1().hashCode() : null);
	}

	public static void propertyEditorTest() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/propertyEditorContext.xml");
		User testBean = applicationContext.getBean("user", User.class);
		System.out.println(testBean);
	}

}
