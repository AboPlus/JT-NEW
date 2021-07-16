package com.abo.jt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abo
 */
@RestController
@RequestMapping
public class ResourceController {
    @PreAuthorize("hasAuthority('/doCreate')")
    @RequestMapping("/doCreate")
    public String doCreate(){
        return "add resource";
    }
    @PreAuthorize("hasAuthority('/doUpdate')")
    @RequestMapping("doUpdate")
    public String doUpdate(){
        return "update resource";
    }
    @PreAuthorize("hasAuthority('/doDelete')")
    @RequestMapping("/doDelete")
    public String doDelete(){
        return "delete resource";
    }
    @PreAuthorize("hasAuthority('/doRetrieve')")
    @RequestMapping("/doRetrieve")
    public String doRetrieve(){
        return "retrieve resource";
    }

}
