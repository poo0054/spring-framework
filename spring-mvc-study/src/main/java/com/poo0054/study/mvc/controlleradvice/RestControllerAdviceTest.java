package com.poo0054.study.mvc.controlleradvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangzhi
 * @date 2023/2/28
 */
@RestControllerAdvice
public class RestControllerAdviceTest {

    @ExceptionHandler
    public String handleException(Exception e) {
        System.out.println(e);
        return e.getMessage();
    }
}
