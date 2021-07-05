package com.abo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @EnableFeignClients:当我们的主启动类被该注解描述时，spring工程在启动时
 * 会扫描@FeignClient注解描述的接口，并基于接口创建其实现类
 * (代理类)
 * @author Abo
 */
@EnableFeignClients
@SpringBootApplication
public class ScaConsumerApplication {
    // 创建日志对象 SLF4J
    private static final Logger log = LoggerFactory.getLogger(ScaConsumerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ScaConsumerApplication.class, args);
        // debug<info<warn<error
        log.warn("==warn==");
        log.debug("==debug==");
        log.info("==info==");
        log.error("==error==");
    }

    /** springboot 工程中可以使用此对象调用第三方服务 */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    /**
     * @Bean注解由Spring提供，通常用于描述方法，用于告诉spring框架
     * 此方法的返回值要交给spring管理，类似@Controller。@Service，@Component(这些注解一般描述类)
     */
    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){
        return new RestTemplate();
    }

    @Service
    public class ConsumerService{
        //流控：链路模式
        @SentinelResource("doConsumerService")
        public String doConsumerService(){
            // 表示通过此方法访问指定资源，例如以后访问数据库
            return "do consumer service";
        }
    }


    @RestController
    public class ConsumerController{
        @Autowired
        private ConsumerService consumerService;

        @Value("${spring.application.name}")
        private String consumerName;
        @Autowired
        private RestTemplate restTemplate;
        // http://localhost:8090/consumer/doRestEcho01
        @GetMapping("/consumer/doRestEcho01")
        public String doRestEcho01() {
            log.debug("/consumer/doRestEcho01 {}","doRestEcho01");
            // 定义服务提供方的地址
            String url = "http://localhost:8081/provider/echo/"+consumerName;
            // 调用服务提供方(sca-provider)
            return restTemplate.getForObject(url, String.class);
        }


        // LoadBalancerClient 此对象低层基于Ribbon实现负载均衡
        // LoadBalancerClient对象再服务启动时低层已经帮我们创建好了
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

        @Autowired
        private RestTemplate loadBalancedRestTemplate;
        //创建一个可实现自增自减功能的一个对象
        private AtomicLong atomicLong = new AtomicLong(0);
        @GetMapping("/consumer/doRestEcho03")
        public String doRestEcho03() throws InterruptedException {
           /* long num = atomicLong.getAndIncrement();//线程安全的自增
            if (num%2 == 0){
                Thread.sleep(200);//模拟耗时操作
                //throw new RuntimeException("..."); //模拟异常比例
            }*/
            //流控规则中的链路限流
            //consumerService.doConsumerService();
            String url = String.format("http://%s/provider/echo/%s", "sca-provider", consumerName);
            return loadBalancedRestTemplate.getForObject(url, String.class);
        }

        @GetMapping("/consumer/doRestEcho04")
        @SentinelResource("doRestEcho04")
        public String doRestEcho04(@RequestParam(required = false) Integer id,
                                   @RequestParam(required = false) String name){
            // %d表示数字的占位符  %s表示字符串的占位符
            return String.format("request id is %d and request name is %s", id, name);
        }


    }

}
