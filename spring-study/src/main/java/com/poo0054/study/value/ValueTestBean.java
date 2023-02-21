package com.poo0054.study.value;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * {@link BeanFactory}
 *
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/22 17:21
 */

public class ValueTestBean {

    @Value("${valueTestBean.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
