package com.poo0054.study.ioc.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/20 14:00
 */
public class SpringIocTest {

    /**
     * spring启动
     */
    @Test
    void springTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(IocConfiguration.class);
        IocTestBean iocTestBean = applicationContext.getBean(IocTestBean.class);
        iocTestBean.send();
        System.out.println("testBean---------------" + iocTestBean);
    }

}
