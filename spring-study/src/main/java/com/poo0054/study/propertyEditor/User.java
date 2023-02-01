package com.poo0054.study.propertyEditor;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/27 11:30
 */

public class User {
    private String name;
    private String age;

    private Interest interest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age='" + age + '\'' + ", interest=" + interest + '}';
    }
}
