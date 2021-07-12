package com.abo.jt.template.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class StringRedisTemplateTests {

    /**此对象为spring提供的一个用于操作redis数据库中的字符串的一个对象*/
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testConnection(){  //测试连接
        String ping = stringRedisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println(ping);//PONG
    }

    @Test
    void testRedisStringOper() throws InterruptedException {
        //获取操作 key,value 的对象
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("ip", "192.168.126.128");
        opsForValue.set("port", "6379");
        String tokenStr = UUID.randomUUID().toString().replace("-", "");
        //设置生命周期
        opsForValue.set("token", tokenStr,1, TimeUnit.SECONDS);
        String ip = opsForValue.get("ip");
        String port = opsForValue.get("port");
        //TimeUnit.SECONDS.sleep(2);
        String token = opsForValue.get("token");
        String result = String.format("the url's ip address is %s and port is the %s and token is %s", ip, port,token);
        System.out.println(result);
    }




}
