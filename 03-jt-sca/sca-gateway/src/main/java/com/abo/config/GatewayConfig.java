package com.abo.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abo
 */
@Configuration
public class GatewayConfig {
    public GatewayConfig(){
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                //String json = "{\"code\":429,\"msg\":\"too many request\"}";
                Map<String,Object> map = new HashMap<>();
                map.put("code", 429);
                map.put("msg", "too many request");

                String json = null;
                // jackson
                /*try {
                    json = new ObjectMapper().writeValueAsString(map);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    json = "{\"code\":429,\"msg\":\"too many request\"}";
                }*/

                // Gson
                //json = new Gson().toJson(map);

                //fastjson
                json = JSON.toJSONString(map);

                return ServerResponse.ok().body(Mono.just(json), String.class);
            }
        });
    }
}
