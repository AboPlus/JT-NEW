package com.abo.jt.template.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class MasterSlaveTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testMasterReadWrite(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("role", "master6379");
        Object role = valueOperations.get("role");
        System.out.println(role);
    }

    @Test
    void testSlaveRead(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object role = valueOperations.get("role");
        System.out.println(role);
    }


}
