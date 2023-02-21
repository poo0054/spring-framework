package com.poo0054.study.customAop.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import com.poo0054.study.customAop.annotation.Aop;

/**
 * @author poo00
 */
public class AopPointcutAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    public static final long serialVersionUID = -7393292428519158720L;

    private final Pointcut pointcut = new MatcherPointcut();

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    private static class MatcherPointcut implements Pointcut, MethodMatcher, ClassFilter {

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            return null != AnnotationUtils.findAnnotation(mostSpecificMethod, Aop.class);
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            return null != AnnotationUtils.findAnnotation(mostSpecificMethod, Aop.class);
        }

        @Override
        public ClassFilter getClassFilter() {
            return this;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return this;
        }

        @Override
        public boolean matches(Class<?> clazz) {
            if (null != AnnotationUtils.findAnnotation(clazz, Aop.class)) {
                return true;
            }
            Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(clazz);
            for (Method allDeclaredMethod : allDeclaredMethods) {
                Aop annotation = AnnotationUtils.findAnnotation(allDeclaredMethod, Aop.class);
                if (null != annotation) {
                    return true;
                }
            }
            return false;
        }
    }
}
