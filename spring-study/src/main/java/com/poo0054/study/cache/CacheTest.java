package com.poo0054.study.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author poo00
 */
@CacheConfig(cacheNames = "test")
public class CacheTest {

    @Cacheable(key = "'cacheTest'")
    public String cacheTest() {
        return "123456";
    }

}
