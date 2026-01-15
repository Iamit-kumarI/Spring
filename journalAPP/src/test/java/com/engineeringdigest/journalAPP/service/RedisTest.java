package com.engineeringdigest.journalAPP.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@RedisHash("RedisTest")
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testEmail(){
        redisTemplate.opsForValue().set("email","amit@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
    }
}
