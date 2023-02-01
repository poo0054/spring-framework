package com.poo0054.study.propertyEditor;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/20 14:00
 */
public class SpringPropertiesTest {

    /**
     * 自定义property
     */
    @Test
    void propertyEditorTest() {
        ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("propertyEditor/propertyEditorContext.xml");
        User testBean = applicationContext.getBean("user", User.class);
        System.out.println(testBean);
    }

}
