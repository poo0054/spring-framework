package com.poo0054.study.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzhi
 * @since 2023/2/22
 */
@RestController
@RequestMapping("test")
public class TestController {

	/**
	 * 测试
	 *
	 * @return test
	 */
	@GetMapping
	public String test() {
//        test1();
		return "test";
	}

	/**
	 * 测试
	 *
	 * @return test
	 */
	@GetMapping("/path/{str}")
	public String testPath(@PathVariable("str") String str) {
		test1();
		return str;
	}

	private void test1() {
		test1();
	}

}
