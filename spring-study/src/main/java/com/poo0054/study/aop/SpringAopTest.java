package com.poo0054.study.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/20 14:00
 */
public class SpringAopTest {

    public static void main(String[] args) {
        aopTest();
    }

    public static void aopTest() {
        ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("aop/SpringAopContext.xml");
        // SimpleBeanImpl testBean = (SimpleBeanImpl) applicationContext.getBean("proxyFactoryBean");
        /*   SimpleService testBean = (SimpleService)applicationContext.getBean("proxyFactoryBean");
        System.out.println(testBean.send());*/

        SimpleService bean = applicationContext.getBean("simpleBeanImpl", SimpleService.class);
        bean.send();
    }
}
