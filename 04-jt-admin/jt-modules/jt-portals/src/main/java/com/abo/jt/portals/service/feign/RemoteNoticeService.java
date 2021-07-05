package com.abo.jt.portals.service.feign;

import com.abo.jt.common.domain.SysNotice;
import com.abo.jt.common.vo.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * value 表示远程服务名
 * contextId为@FeignClient注解描述的接口的类全名，然后首字母小写
 * @author Abo
 */
@FeignClient(value = "jt-system", contextId = "remoteNoticeService")
public interface RemoteNoticeService {
    /** 这个url要和调用的远程服务的url相同 */
    @GetMapping("/notice/")
    JsonResult doFindNotices(SysNotice notice);
    //  ---request--->(/protals/notice/)---new request--->/notice/
}
