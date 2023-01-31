package com.poo0054.study.customAop;

import java.lang.reflect.Method;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotationUtils;

import com.poo0054.study.customAop.annotation.Aop;

/**
 * @author poo00
 */
public class AopPointcutAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    public static final long serialVersionUID = -7393292428519158720L;

    private final StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            // TODO 添加缓存
            MethodClassKey methodClassKey = new MethodClassKey(method, targetClass);
            Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            // Aop annotation = mostSpecificMethod.getAnnotation(Aop.class);
            Aop annotation = AnnotationUtils.findAnnotation(mostSpecificMethod, Aop.class);
            if (null != annotation) {
                return true;
            }
            return false;
        }
    };

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
