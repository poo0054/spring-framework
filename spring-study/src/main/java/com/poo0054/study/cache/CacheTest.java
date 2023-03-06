package com.poo0054.study.cache;

import com.poo0054.study.propertyEditor.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author poo00
 */
@CacheConfig(cacheNames = "test")
public class CacheTest {

	@Cacheable(key = "#str+'::arg'")
	@Transactional
	public User cacheTest(String str) {
		User user = new User();
		return user;
	}

}
