package com.poo0054.study.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/4 14:01
 */
@EnableCaching
@Configuration
public class Config {

    @Bean
    public CacheTest cacheTest() {
        return new CacheTest();
    }

    @Bean
    public CacheManager ehCacheCacheManager() {
        return new NoOpCacheManager();
    }
}