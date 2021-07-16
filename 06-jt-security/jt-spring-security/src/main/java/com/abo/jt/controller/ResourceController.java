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

    /**
     * hasAuthority('/doCreate')
     * 访问doCreate方法必须拥有/doCreate 权限
     * @return
     */
    @PreAuthorize("hasAuthority('sys:resource:create')")
    @RequestMapping("/doCreate")
    public String doCreate(){
        return "add resource";
    }

    /**
     * hasAnyRole('jinpai')
     * 访问doUpdate方法时必须拥有jinpai角色
     * @return
     */
    @PreAuthorize("hasAnyRole('jinpai')")
    @RequestMapping("doUpdate")
    public String doUpdate(){
        return "update resource";
    }

    @PreAuthorize("hasAuthority('/doDelete')")
    @RequestMapping("/doDelete")
    public String doDelete(){
        return "delete resource";
    }

    @PreAuthorize("hasAuthority('sys:resource:retrieve')")
    @RequestMapping("/doRetrieve")
    public String doRetrieve(){
        return "retrieve resource";
    }

}
