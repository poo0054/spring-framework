package com.poo0054.study.aop.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;

import com.poo0054.study.aop.xml.MyAspect;

/**
 * configuration需要{@link org.springframework.context.annotation.ConfigurationClassPostProcessor}进行处理
 * 然后校验是否存在{@link ImportBeanDefinitionRegistrar}的注解 并进行注入 <br/>
 * {@link ImportBeanDefinitionRegistrar}需要 {@link EnableAspectJAutoProxy}处理
 * 
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/12/15 14:01
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }

    @Bean
    public SimpleService simpleService() {
        return new SimpleServiceImpl();
    }

}
