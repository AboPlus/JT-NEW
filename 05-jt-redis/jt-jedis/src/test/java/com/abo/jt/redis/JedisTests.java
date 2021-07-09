package com.abo.jt.redis;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class JedisTests {

    @Value("${jedis.host}")
    private String host;
    @Value("${jedis.port}")
    private int port;

    /**
     * 基于jedis操作redis中的字符串
     * 场景应用：
     *      1.保存登录时的验证码
     *      2.手机短信验证码
     *      3.购物车数量递增
     *      4.博客文字数量
     *      5.  ....
     * @throws InterruptedException
     */
    @Test
    void testStringOper() throws InterruptedException {
        //构建redis的客户端对象Jedis
        // 注意：需要在redis服务端将bind xxx.xxx注释掉，将protected-mode改为no,改完后重启redis服务
        Jedis jedis = new Jedis(host,port);
        //jedis.auth("123456")  //假如设置了密码还需要这个动作
        //测试是否连接到了redis
        String ping = jedis.ping();
        System.out.println(ping);
        //向数据库存储数据
        jedis.set("id", "10001");
        jedis.set("name", "abo");
        jedis.incrBy("id", 10);
        jedis.set("token", UUID.randomUUID().toString().replace("-", ""));
        //设置key有效时长
        jedis.expire("token", 60*60*24*7);
        //从数据库取数据
        String id = jedis.get("id");
        String name = jedis.get("name");
        //休眠一秒
        TimeUnit.SECONDS.sleep(1);
        String token = jedis.get("token");
        String result = String.format("The person's id is %s and his name is %s and token's vakye is %s", id, name, token);
        System.out.println(result);
        jedis.close();
    }

    @Test
    void testJsonOper(){
        // 构建对象
        Map<String,Object> map = new Hashtable<>();
        map.put("id", 100);
        map.put("title", "Spring 认证");
        map.put("content", "very good");
        //将对象转化为json格式字符串
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        // 将json字符串写入到redis
        Jedis jedis = new Jedis(host,port);
        jedis.set("user", jsonStr);
        //读取redis中数据
        jsonStr = jedis.get("user");
        System.out.println(jsonStr);
        Map jedisMap = gson.fromJson(jsonStr, Map.class);
        System.out.println(jedisMap);
        jedis.close();
    }

    @Test
    void testRedisHash(){
        //1.建立连接
        Jedis jedis = new Jedis(host,port);
        //2.存储一篇博客信息
        Map<String,String> map = new HashMap<>();
        map.put("id", "10001");
        map.put("author", "Abo");
        map.put("tag","Redis NoSQL");
        map.put("title", "this is jedis's hash methods,that's cool, bro");
        map.put("content", "this is a good article");
        map.put("created", "2021-7-9 11:50:45");
        map.put("updated", "2021-7-9 11:50:45");
        jedis.hmset("blog", map);
        //3.获取博客内容并输出
        List<String> hmget = jedis.hmget("blog", "id", "author", "tags", "title", "content", "created", "updated");
        System.out.println("hmget's value is :" + hmget);
        Map<String, String> hgetAll = jedis.hgetAll("blog");
        System.out.println("hgetAll's value is :" + hgetAll);
        //4.释放资源
        jedis.close();
    }

    /**
     * 实现一个秒杀队列
     */
    @Test
    void testRedisList(){
        //1.连接redis
        Jedis jedis = new Jedis(host,port);
        //2.向队列存数据
        jedis.rpush("spick", "user1","user2","user3","user4");
        //3.按FIFO(First Input First Output)(先进先出)的顺序从队列取数据
        Long times = jedis.llen("spick");
        for (int i = 1 ; i<=times; i++){
            if (jedis.llen("spick") == 0) {
                break;
            }else {
                String spick = jedis.lpop("spick");
                System.out.println("times is : " + i + " and user is: " + spick);
            }
        }
        //4.释放资源
        jedis.close();
    }

    /**
     * 实现朋友圈点赞功能
     */
    @Test
    void testSet(){
        //1.连接redis
        Jedis jedis = new Jedis(host,port);
        //2.朋友圈点赞
        jedis.sadd("count", "1","2","3");
        //3.取出点赞数
        Long count = jedis.scard("count");
        System.out.println("the person's zone get value is ：" + count);
        //4.释放资源
        jedis.close();
    }


}
