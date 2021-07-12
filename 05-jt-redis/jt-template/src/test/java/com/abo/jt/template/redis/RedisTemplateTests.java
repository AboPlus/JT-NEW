package com.abo.jt.template.redis;

import com.abo.jt.template.pojo.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RedisTemplateTests {
    /**
     * 通过此对象操作redis中复杂数据类型的数据，例如hash结构
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testConnection(){
        String ping = redisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println(ping);
    }

    @Test
    void testStringOper(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //RedusTemplate存储数据时会默认基于JDK的序列化机制，将数据序列化以后再进行存储
        valueOperations.set("id", "1001");
        valueOperations.set("name", "Abo");
        //对数据进行反序列化实现
        Object name = valueOperations.get("name");
        System.out.println(name);
    }

    @Test
    void testSetData(){
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("like","1","2","3","3","4");
        Long count = setOperations.size("like");
        System.out.println("the person's content has got the like ： " + count);
    }

    @Test
    void testHashData01(){
        //获取操作Hash数据的对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map<String,String> map = new HashMap<>();
        map.put("id", "10001");
        map.put("author", "Abo");
        map.put("tag","Redis NoSQL");
        map.put("title", "this is jedis's hash methods,that's cool, bro");
        map.put("content", "this is a good article");
        map.put("created", "2021-7-9 11:50:45");
        map.put("updated", "2021-7-9 11:50:45");
        hashOperations.putAll("blog",map);
        //从redis中获取存储的hash数据
        Object o = hashOperations.get("blog", "content");
        System.out.println(o);
        //获取blog这个key对应的所有属性以及属性的值
        Map blog = hashOperations.entries("blog");
        System.out.println(blog);
    }

    @Test
    void testListOper(){
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush("A", "100");
        listOperations.leftPush("A", "101");
        listOperations.leftPush("A", "102");
        Object a1 = listOperations.rightPop("A");
        Object a2 = listOperations.rightPop("A");
        Object a3 = listOperations.rightPop("A");
        System.out.println(a1+"/"+a2+"/"+a3);
    }

    /**
     * 定义清楚数据库数据的方法
     */
    @Test
    void testFlushDB(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushAll();
                return "flush ok";
            }
        });
    }

    @Test
    void testStringOper02(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Blog blog = new Blog();
        blog.setId(10);
        blog.setTitle("study redis");
        valueOperations.set("blog", blog);//序列化
        Object o = valueOperations.get("blog");//反序列化
        System.out.println("blog's value is " + o );
    }




}
