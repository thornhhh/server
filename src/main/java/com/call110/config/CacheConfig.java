package com.call110.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
	private final static Log log = LogFactory.getLog(CacheConfig.class);
	@Bean
    public EhCacheManager cacheManager() {
		log.info("+++++++++++++++EhCacheManager++++++++++++++++");
		EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache.xml");
        return em;  
    }
}
