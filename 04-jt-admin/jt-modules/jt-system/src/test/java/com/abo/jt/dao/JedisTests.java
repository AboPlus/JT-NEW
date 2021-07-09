package com.abo.jt.dao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class JedisTests {


    /**
     * 连接redis
     */
    @Test
    void testRedisStringOper(){
        Jedis jedis = new Jedis("192.168.126.128",6379);
        jedis.set("id", "101");
        jedis.set("name", "Abo");
        String id = jedis.get("id");
        String name = jedis.get("name");
        System.out.println("===set ok===");

        id = jedis.incr("id").toString();
        id = jedis.incrBy("id", 10).toString();

        String result = String.format("The person's id is %s and his name is %s", id, name);
        System.out.println(result);
    }

    /**
     * 连接池
     */
    @Test
    void testJedisPool(){
        //构建连接池配置信息
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(200);
        //构建连接池
        JedisPool jedisPool = new JedisPool("192.168.126.128",6379);
        //从连接池中获取连接
        Jedis jedis = jedisPool.getResource();
        //读取数据
        String name = jedis.get("name");
        System.out.println(name);
        //释放连接池
        jedisPool.close();
    }

}
