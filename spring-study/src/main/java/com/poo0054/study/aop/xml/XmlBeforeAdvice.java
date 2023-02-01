package com.poo0054.study.aop.xml;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/6/28 17:47
 */
public class XmlBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("before class  " + target.getClass() + " call  " + method.getName());
    }

}
