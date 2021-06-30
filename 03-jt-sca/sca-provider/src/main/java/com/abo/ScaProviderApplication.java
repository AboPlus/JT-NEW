package com.abo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abo
 */
@SpringBootApplication
public class ScaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaProviderApplication.class, args);
    }

    @Value("${server.port}")
    private String server;

    /**
     * @RefreshScope的作用是，在配置中心的相关配置发生变化以后，能够及时看到更新
     */
    @RefreshScope
    @RestController
    public class ProviderController {

        /**
         * @Value("${logging.level.com.abo:error}")中冒号后面是默认值，
         * 即如果没有取到前面的值就使用冒号后面的默认值
         */
        @Value("${logging.level.com.abo:error}")
        private String logLevel;
        @Value("${server.tomcat.threads.max:200}")
        private Integer serverThreadMax;
        @Value("${page.pageSize:10}")
        private Integer pageSize;

        @GetMapping("/provider/doGetLogLevel")
        public String doGetLogLevel(){
            return "log level is " + logLevel;
        }

        @GetMapping("/provider/doGetMaxThread")
        public String doGetMaxThread(){
            return "tomcat max thread is " + serverThreadMax;
        }

        @GetMapping("/provider/doGetPageSize")
        public String doGetPageSize(){
            return " page size is " + pageSize;
        }

        @GetMapping("/provider/echo/{msg}")
        public String doEcho(@PathVariable String msg){
            return server + "say:Hello Nacos Discovery " + msg;
        }



    }

}
