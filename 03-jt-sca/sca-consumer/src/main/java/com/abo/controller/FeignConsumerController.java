package com.abo.controller;

import com.abo.service.RemoteProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abo
 */
@RestController
@RequestMapping("/consumer/")
public class FeignConsumerController {
    /** Feign API */
    @Autowired
    private RemoteProviderService remoteProviderService;

    /** http://ip:port/consumer/hello */
    @GetMapping("/echo/{msg}")
    public String doEcho(@PathVariable String msg){
        // 执行远程调用
        return remoteProviderService.doEcho(msg);
    }
}
