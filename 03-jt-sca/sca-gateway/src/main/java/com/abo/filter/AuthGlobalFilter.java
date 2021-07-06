package com.abo.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 认证过滤器，主要用于检查请求是否已认证
 * @author Abo
 */
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    /**
     * 重写GlobalFilter接口中的方法
     * 通过filter方法处理客户端的请求
     * @param exchange
     * @param chain
     * @return chain.filter(exchange); 执行过滤链中下一个过滤器,不仅仅只有一个过滤器
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //通过exchange拿到请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求参数
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String token = queryParams.get("token").get(0);
        System.out.println("tokenValue= " + token);
        // 检查请求参数中是否有token，token的值是否为admin
        if (token==null || !"admin".equals(token)) {
            ServerHttpResponse response = exchange.getResponse();
            // 设置还没有认证的状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //表示处理已完成
            return response.setComplete();
        }
        // 假如已认证，则执行下一步操作(下一个过滤器)
        System.out.println("===AuthGlobalFilter.filter===");
        return chain.filter(exchange);
    }

    /**
     * 重写Ordered接口中的方法
     * 定义过滤器的优先级，其中返回的数字越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return -100;
    }
}
