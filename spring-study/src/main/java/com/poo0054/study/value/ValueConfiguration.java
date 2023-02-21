package com.poo0054.study.value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhangzhi
 * @date 2023/2/1 11:42
 */
@Configuration
@PropertySource(value = "classpath:/valueProperties/a.properties", encoding = "UTF-8")
public class ValueConfiguration {

    @Bean
    public ValueTestBean valueTestBean() {
        return new ValueTestBean();
    }
}
