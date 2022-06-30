package com.poo0054;

import com.poo0054.study.TestBean;
import com.poo0054.study.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/20 14:00
 */
public class TestSpring {

	public static void main(String[] args) {
		springTest();
	}

	public static void springTest() {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("simpleContext.xml");
//		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("simpleContext.xml");
		TestBean testBean = applicationContext.getBean("testBean", TestBean.class);
//		TestBean1 testBean1 = applicationContext.getBean("testBean1", TestBean1.class);
		System.out.println("testBean---------------" + testBean);
//		System.out.println("testBean1---------------" + testBean.getBean1() != null ? testBean.getBean1().hashCode() : null);
	}

	public static void propertyEditorTest() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("propertyEditorContext.xml");
		User testBean = applicationContext.getBean("user", User.class);
		System.out.println(testBean);
	}

}
