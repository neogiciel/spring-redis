package com.neogiciel.springsearch.redis;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.neogiciel.springsearch.model.Personne;

@Configuration
@EnableCaching
public class RedisConfig {


     //Creating Connection with Redis
    /*@Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }*/

    /*@Bean
    public RedisTemplate<String, Personne> redisTemplate(){
        RedisTemplate<String, Personne> empTemplate = new RedisTemplate<>();
        empTemplate.setConnectionFactory(redisConnectionFactory());
        return empTemplate;
    }*/

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManagerBuilder
            .fromConnectionFactory(connectionFactory)
            .withCacheConfiguration("myCache",
                RedisCacheConfiguration.defaultCacheConfig()
                    //.entryTtl(Duration.ofMinutes(10))
                    .entryTtl(Duration.ofSeconds(60))
            )
            .build();
    }

    /*
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
            .withCacheConfiguration("customCache",
                RedisCacheConfiguration.defaultCacheConfig()
                    //.entryTtl(Duration.ofMinutes(5))
                    .entryTtl(Duration.ofSeconds(60)) 
            );
    }*/
}