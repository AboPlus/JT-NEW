package com.abo.jt.portals.controller;

import com.abo.jt.common.domain.SysNotice;
import com.abo.jt.common.vo.JsonResult;
import com.abo.jt.portals.service.feign.RemoteNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abo
 */
@RestController
@RequestMapping("/portals/")
public class PortalsController {

    @Autowired
    private RemoteNoticeService remoteNoticeService;

    @GetMapping("notice")
    public JsonResult doFindNotices(SysNotice notice){
        return remoteNoticeService.doFindNotices(notice);
    }

}
