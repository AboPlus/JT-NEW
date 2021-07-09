package com.abo.jt.template.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class StringRedisTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testConnection(){  //测试连接
        String ping = stringRedisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println(ping);//PONG
    }





}
