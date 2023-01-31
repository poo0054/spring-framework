package com.poo0054.study.customAop.annotation;

import java.lang.annotation.*;

/**
 * @author poo00
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Aop("我是嵌套在Nesting的注解")
public @interface Nesting {

}
