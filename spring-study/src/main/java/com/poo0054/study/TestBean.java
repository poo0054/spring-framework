package com.poo0054.study;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@link BeanFactory}
 *
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/22 17:21
 */

public class TestBean {

	private TestBean1 bean1;

	@Autowired
	public void setBean1(TestBean1 bean1) {
		System.out.println("我是TestBean 准备  add " + bean1);
		this.bean1 = bean1;
	}

	public TestBean1 getBean1() {
		return bean1;
	}

	public void send() {
		System.out.println("----TestBean---send----------------------------------------------------------" + bean1);
	}


}
