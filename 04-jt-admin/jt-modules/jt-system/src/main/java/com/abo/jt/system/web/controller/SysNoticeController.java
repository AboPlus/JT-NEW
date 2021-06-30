package com.abo.jt.system.web.controller;

import com.abo.jt.system.domain.SysNotice;
import com.abo.jt.system.service.SysNoticeService;
import com.abo.jt.system.web.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Abo
 */
@RestController
@RequestMapping("/notice/")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;

    @GetMapping
    public JsonResult doFindNotices(SysNotice notice){
        return new JsonResult(sysNoticeService.selectNotices(notice));
    }

    @GetMapping("{id}")
    public JsonResult doSelectById(@PathVariable Long id){
        SysNotice sysNotice = sysNoticeService.selectById(id);
        return new JsonResult(sysNotice);
    }

    @DeleteMapping("{id}")
    public JsonResult doDeleteById(@PathVariable Long ...id){
        int rows = sysNoticeService.deleteNotice(id);
        return new JsonResult("delete rows is : "+rows);
    }

    @PostMapping
    public JsonResult doInsertObject(@RequestBody SysNotice notice){
        int rows = sysNoticeService.insertNotice(notice);
        return new JsonResult("insert rows is : " + rows);
    }

    @PutMapping
    public JsonResult doUpdateObject(@RequestBody SysNotice notice){
        int rows = sysNoticeService.insertNotice(notice);
        return new JsonResult("update rows is " + rows);
    }


}
