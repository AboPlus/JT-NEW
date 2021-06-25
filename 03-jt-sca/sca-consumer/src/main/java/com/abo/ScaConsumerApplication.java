package com.abo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Abo
 */
@SpringBootApplication
public class ScaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaConsumerApplication.class, args);
    }

    /** springboot 工程中可以使用此对象调用第三方服务 */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @RestController
    public class ConsumerController{

        @Value("${spring.application.name}")
        private String consumerName;
        @Autowired
        private RestTemplate restTemplate;
        // http://localhost:8090/consumer/doRestEcho01
        @GetMapping("/consumer/doRestEcho01")
        public String doRestEcho01() {
            // 定义服务提供方的地址
            String url = "http://localhost:8081/provider/echo/"+consumerName;
            // 调用服务提供方(sca-provider)
            return restTemplate.getForObject(url, String.class);
        }


        //LoadBalancerClient 此对象低层基于Ribbon实现负载均衡
        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @GetMapping("/consumer/doRestEcho02")
        public String doRestEcho02() {
            // 基于服务采用一定的负载均衡算法获取服务实例
            ServiceInstance choose = loadBalancerClient.choose("sca-provider");
            String ip = choose.getHost();
            int port = choose.getPort();
            // 定义服务提供方的地址
            // String url = "http://"+ip+":"+port+"/provider/echo/"+consumerName;
            // 假如不希望使用字符串拼接，可以使用如下方式(其中%s为占位符，后面传入的参数依次为%s的值)
            String url = String.format("http://%s:%s/provider/echo/%s", ip, port, consumerName);
            // 调用服务提供方(sca-provider)
            return restTemplate.getForObject(url, String.class);
        }


    }

}
