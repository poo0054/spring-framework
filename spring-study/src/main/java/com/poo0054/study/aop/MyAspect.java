package com.poo0054.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/6/28 17:47
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.poo0054.study.aop.SimpleService.*(..))")
    private void pointCut() {}

    @Around("pointCut()")
    public Object touchBean(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("I is  Around before");
        Object proceed = joinPoint.proceed();
        System.out.println("I is Around after");
        return proceed;
    }
}
