package com.abo.jt.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

@SpringBootTest
public class JedisTransactionTests {
    @Value("${jedis.host}")
    private String host;
    @Value("${jedis.port}")
    private int port;

    @Test
    void testTransaction(){
        Jedis jedis = new Jedis(host,port);
        //开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("ticket", "100");
            multi.set("money", "0");
            //int num = 0/0; //模拟出现异常
            //提交事务
            multi.exec();
        }catch (Exception e){
            e.printStackTrace();
            //事务取消
            multi.discard();
        }finally {
            jedis.close();
        }


    }
}
