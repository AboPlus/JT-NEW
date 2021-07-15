package com.abo.jt.template.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisClusterTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testRedisCluster(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("sex", "boy");
        Object sex = valueOperations.get("sex");
        System.out.println(sex);
    }


}
