package com.poo0054.study.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/6/28 17:47
 */
public class MyXmlAspect implements MethodBeforeAdvice {


	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println(" before  " + method.getName());
	}


}
