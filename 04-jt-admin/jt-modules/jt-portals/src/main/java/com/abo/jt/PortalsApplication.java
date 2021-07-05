package com.abo.jt;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Abo
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class PortalsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalsApplication.class, args);
    }

    /**
     * Feign方式调用时的请求拦截器
     * 通过此拦截器拦截到请求数据以后，可以结合业务对请求进行预处理
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            /** 在此方法中可以通过requestTemplate对象向新的请求中写数据 */
            @Override
            public void apply(RequestTemplate requestTemplate) {
                //1.获取原有请求中的数据
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = requestAttributes.getRequest();
                //2.将原有请求中数据添加到新的请求中
                // requestTemplate.header();//向请求头添加数据
                Map<String, String[]> parameterMap = request.getParameterMap();
                System.out.println("keys = "+parameterMap.keySet());
                if (parameterMap.containsKey("pageCurrent")){
                    requestTemplate.query("pageCurrent", request.getParameter("pageCurrent"));
                    requestTemplate.query("pageSize", request.getParameter("pageSize"));
                }
            }
        };
    }

}
