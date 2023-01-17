package com.poo0054.study.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author poo00
 */
@CacheConfig
public class CacheTest {

    @Cacheable
    public String cacheTest() {
        return "123456";
    }

}
