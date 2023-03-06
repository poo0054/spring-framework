package com.poo0054.study.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/4 14:01
 */
@EnableCaching
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class CacheConfig {

	@Bean
	public CacheTest cacheTest() {
		return new CacheTest();
	}

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}

	@Bean
	public AspectTest aspectTest() {
		return new AspectTest();
	}

}