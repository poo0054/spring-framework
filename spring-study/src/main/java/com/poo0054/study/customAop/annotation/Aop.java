package com.poo0054.study.customAop.annotation;

import java.lang.annotation.*;

/**
 * @author poo00
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Aop {
    String value();
}
