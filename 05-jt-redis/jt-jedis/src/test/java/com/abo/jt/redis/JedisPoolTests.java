package com.abo.jt.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

@SpringBootTest
public class JedisPoolTests {
    @Value("${jedis.host}")
    private String host;
    @Value("${jedis.port}")
    private int port;

    @Test
    void testJedisPool(){
        //1.创建池对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);  //最大连接数
        jedisPoolConfig.setMaxIdle(60);     //最大空闲时间
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port);
        //2.从池中获取连接
        Jedis jedis = jedisPool.getResource();
        //3.操作redis数据
        Set<String> keys = jedis.keys("*");
        //4.释放资源
        System.out.println(keys);
        jedis.close();
        //jedisPool.close();
    }



}
