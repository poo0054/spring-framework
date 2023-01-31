package com.poo0054.study.customAop;

import com.poo0054.study.customAop.annotation.Nesting;

/**
 * @author poo00
 */
public class AopTest {

    @Nesting
    public String aop() {
        return "123456";
    }

}
