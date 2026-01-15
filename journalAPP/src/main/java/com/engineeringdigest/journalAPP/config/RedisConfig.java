package com.engineeringdigest.journalAPP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration//becz its a bean class of redis
public class RedisConfig {
    //the problem is what we are setting in spring boot that is not visible in redis cli
    //and what we are setting in cli that is not visible to sprind boot to make this serialize
    //and deserialize error solve we have do this so that same key and values are attached everywhere

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){//redis connectionfactory helps create connection between springboot and redis server
        RedisTemplate redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
