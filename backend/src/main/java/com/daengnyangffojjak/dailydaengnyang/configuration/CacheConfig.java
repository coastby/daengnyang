package com.daengnyangffojjak.dailydaengnyang.configuration;

import com.daengnyangffojjak.dailydaengnyang.utils.cache.CacheType;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
	@Bean
	public List<CaffeineCache> caffeineConfig() {
		return Arrays.stream(CacheType.values())
				.map(cache -> new CaffeineCache(cache.getCacheName(), Caffeine.newBuilder()
						.recordStats()
						.expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.SECONDS)
						.maximumSize(cache.getMaximumSize())
						.build()))
				.toList();
	}

	@Bean
	public CacheManager cacheManager(List<CaffeineCache> caffeineCaches) {
		final SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(caffeineCaches);
		return cacheManager;
	}
}
