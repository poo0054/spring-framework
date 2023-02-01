package com.poo0054.study.aop.ee;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.lang.Nullable;

import com.poo0054.study.aop.annotation.SimpleService;

class TestBeanAdvisor extends StaticMethodMatcherPointcutAdvisor implements Serializable {

    private static final long serialVersionUID = 1917255554909574730L;

    public int count;

    public TestBeanAdvisor() {
        setAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, @Nullable Object target) {
                System.out.println("i before ");
                ++count;
            }
        });
    }

    @Override
    public boolean matches(Method method, @Nullable Class<?> targetClass) {
        return SimpleService.class.isAssignableFrom(targetClass);
    }

}