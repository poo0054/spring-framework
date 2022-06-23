package com.poo0054.study;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * {@link BeanFactory}
 *
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/22 17:21
 */

public class TestBean implements BeanPostProcessor {

	private TestBean1 testBean1;

	public void setTestBean1(TestBean1 testBean1) {
		System.out.println("----TestBean---setTestBean1--------testBean1--------------------------------------------------");
		this.testBean1 = testBean1;
	}

	public TestBean() {
		System.out.println("------TestBean   init ---------");
	}

	public void send() {
		System.out.println("----TestBean---send----------------------------------------------------------");
	}


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("------postProcessBeforeInitialization-" + bean + "---------" + beanName + "-----------");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("------postProcessAfterInitialization-" + bean + "---------" + beanName + "-----------");
		return bean;
	}


}
