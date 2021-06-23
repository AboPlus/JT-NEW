package com.cy.jt.demo.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Abo
 */
@Service
public class MemoryService {

    /*private Map<String,Object> cache = new ConcurrentHashMap<>();*/
    /** 本地缓存（JVM内部的缓存） */
    @Cacheable(value = "memoryCache")  //由此注解描述的方法为缓存切入点
    public List<Map<String,Object>> list(){
        /*if (cache.containsKey("memoryKey")) {
            //将来这个key为参数列表的组合
            return (List<Map<String,Object>>)cache.get("memoryKey");
        }*/
        System.out.println("Get Data from DataBase ");
        // 假设如下数据来自数据库
        Map<String,Object> m1 = new HashMap<>();
        m1.put("id", 100);
        m1.put("title", "title-A");
        Map<String,Object> m2 = new HashMap<>();
        m2.put("id", 101);
        m2.put("title", "title-B");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        /*cache.put("memoryKey", list);*/
        return list;
    }

}
