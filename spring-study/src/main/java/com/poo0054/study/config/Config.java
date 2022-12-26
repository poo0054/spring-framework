package com.poo0054.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/12/20 11:14
 */
@Configuration
public class Config {

    @Bean
    public TestBean testBean() {
        return new TestBean();
    }
}
