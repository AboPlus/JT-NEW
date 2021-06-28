package com.abo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abo
 */
@RestController
@RequestMapping("/consumer/")
public class RestConsumerController {

    @Autowired
    private RestTemplate loadBalancedRestTemplate;

    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable String id){
        String url = String.format("http://%s/provider/%s", "sca-provider", id);
        //loadBalancedRestTemplate.delete(url, id);
        //return " delete ok ";
        // 假如需要获取服务提供方法，执行的删除操作的结果，可以通过如下方式进行实现
        ResponseEntity<String> exchange =
                loadBalancedRestTemplate.exchange(
                        url, // 请求url
                        HttpMethod.DELETE,  //请求方式
                        null,  //请求实体参数
                        String.class);      //响应数据的类型
        return exchange.getBody();  //响应体中的数据
    }

    @PostMapping
    public Map<String,Object> doCreate(@RequestBody Map<String,Object> map){
        // System.out.println("consumer.map = " + map);
        String url = String.format("http://sca-provider/provider/");
        return loadBalancedRestTemplate.postForObject(url, map, Map.class);
    }

    @PutMapping
    public Map<String,Object> doUpdate(@RequestBody Map<String,Object> map){
        System.out.println("consumer.request= "+map);
        String url = String.format("http://sca-provider/provider/");
        //loadBalancedRestTemplate.put(url, map);
        //return "update ok";
        // 假如希望获取服务提供方执行更新操作时的响应结果，可以采用如下方式
        HttpEntity requestEntity = new HttpEntity(map);
        ResponseEntity<Map> exchange = loadBalancedRestTemplate.exchange(
                url,    //请求url
                HttpMethod.PUT, // 请求方式
                requestEntity,  // 请求实体
                Map.class);     //响应数据类型
        return exchange.getBody();  // 获取响应体数据
    }


}
