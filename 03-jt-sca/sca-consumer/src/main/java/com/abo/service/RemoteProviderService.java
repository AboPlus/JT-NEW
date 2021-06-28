package com.abo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @FeignClient:该注解描述接口时，用于告诉spring要为此接口创建实现类
 * @FeignClient("sca-provider")：value的值为要调用的服务名
 * @author Abo
 */
@FeignClient(value = "sca-provider", contextId = "remoteProviderService")
public interface RemoteProviderService {

    /**
     * @PathVariable("msg"):在Feign接口中的方法定义时，假如使用了@PathVariable注解描述方法
     * 参数，还需要在@PathVariable注解内部定义一个参数名，与url中{}表达式指定的参数名相同
     * 假如注解中的名字没有指定就会爆异常
     * @param msg
     * @return
     */
    @GetMapping("/provider/echo/{msg}")
    String doEcho(@PathVariable("msg") String msg);
}
