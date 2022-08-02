package com.poo0054.study.listener;

import org.springframework.context.ApplicationListener;

/**
 * 监听器测试
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/2 10:35
 */
public class ApplicationListenerDemo implements ApplicationListener<ApplicationEventDemo> {

	@Override
	public void onApplicationEvent(ApplicationEventDemo event) {
/*		int i1 = 0;
		int i2 = 0;
		int i = i1 / i2;*/
		System.out.println("------------------DemoListener-------------------" + event);
	}
}
