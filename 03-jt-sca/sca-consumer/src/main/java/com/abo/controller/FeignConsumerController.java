package com.abo.controller;

import com.abo.service.RemoteProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
