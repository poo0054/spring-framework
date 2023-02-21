package com.poo0054.study.ioc.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangzhi
 * @date 2023/2/1 11:42
 */
@Configuration
public class IocConfiguration {

    @Bean
    public IocTestBean iocTestBean() {
        return new IocTestBean();
    }
}
