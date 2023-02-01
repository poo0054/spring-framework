package com.poo0054.study.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author poo00
 */
@CacheConfig(cacheNames = "test")
public class CacheTest {

    @Cacheable(key = "#str+'::arg'")
    public String cacheTest(String str) {
        return "123456" + str;
    }

}
