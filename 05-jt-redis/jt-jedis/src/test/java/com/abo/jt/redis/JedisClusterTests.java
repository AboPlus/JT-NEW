package com.abo.jt.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class JedisClusterTests {
    @Test
    void testRedisCluster(){
        //1.构建集群对象JedisCluster
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.126.128", 8010));
        nodes.add(new HostAndPort("192.168.126.128", 8011));
        nodes.add(new HostAndPort("192.168.126.128", 8012));
        nodes.add(new HostAndPort("192.168.126.128", 8013));
        nodes.add(new HostAndPort("192.168.126.128", 8014));
        nodes.add(new HostAndPort("192.168.126.128", 8015));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //2.读写redis集群数据
        jedisCluster.set("city", "beijing");
        String city = jedisCluster.get("city");
        System.out.println(city);
        for (int i = 0; i<100 ; i++) {
            jedisCluster.set("num"+i, "var-"+i);
        }

    }
}
