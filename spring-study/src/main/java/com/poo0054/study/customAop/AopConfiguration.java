package com.poo0054.study.customAop;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author poo00
 */
@Configuration
public class AopConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public AopPointcutAdvisor aopPointcutAdvisor() {
        AopPointcutAdvisor aopPointcutAdvisor = new AopPointcutAdvisor();
        aopPointcutAdvisor.setAdvice(aopInterceptor());
        return aopPointcutAdvisor;
    }

    @Bean
    public AopInterceptor aopInterceptor() {
        return new AopInterceptor();
    }

}
