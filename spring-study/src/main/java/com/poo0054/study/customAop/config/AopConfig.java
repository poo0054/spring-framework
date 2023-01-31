package com.poo0054.study.customAop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.poo0054.study.customAop.AopTest;
import com.poo0054.study.customAop.annotation.EnableAop;

/**
 * @author poo00
 */
@EnableAop
@Configuration
public class AopConfig {

    @Bean
    public AopTest aopTest() {
        return new AopTest();
    }
}
