package com.sama.springbootdemo01.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 使用redis缓存session，并设置session失效时间
 * @author fjk
 * @data 2019-12-01
 * @since jdk 1.8
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)
public class RedisSessionConfig {
}
