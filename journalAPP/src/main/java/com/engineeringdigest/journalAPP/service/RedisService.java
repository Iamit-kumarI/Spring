package com.engineeringdigest.journalAPP.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key,Class<T>entityClass){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper=new ObjectMapper();//we are mapping the object with entity class of weather
            return objectMapper.readValue(o.toString(),entityClass);
        } catch (Exception e) {
            log.error("Exception: "+e);
            return null;
        }
    }
    public void set(String key,Object o,Long ttl){
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            String json= objectMapper.writeValueAsString(0);
            redisTemplate.opsForValue().set(key,json,ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception: "+e);
        }
    }
}
