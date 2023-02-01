package com.poo0054.study.aop.ee;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/6/28 17:47
 */
public class ServiceImpl implements Service {

    @Override
    public String send() {
        System.out.println("----------------SimpleBean---send---------------------");
        return "i is return";
    }

}
