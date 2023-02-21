package com.poo0054.study.customAop.interceptor;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import com.poo0054.study.customAop.annotation.Aop;

/**
 * @author poo00
 */
public class AopInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        Supplier<Object> supplier = () -> {
            try {
                return invocation.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
        return execute(supplier, invocation.getThis(), method, invocation.getArguments());
    }

    private Object execute(Supplier<Object> supplier, Object target, Method method, Object[] arguments) {
        Aop annotation = AnnotationUtils.findAnnotation(method, Aop.class);
        System.out.println(annotation.value());
        return supplier.get();
    }
}
