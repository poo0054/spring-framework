package com.poo0054.study.aop.annotation;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/6/28 17:47
 */
public class SimpleServiceImpl implements SimpleService {

	@Override
	public String send() {
		System.out.println("----------------SimpleBean---send---------------------");
		return "i is return";
	}

}
