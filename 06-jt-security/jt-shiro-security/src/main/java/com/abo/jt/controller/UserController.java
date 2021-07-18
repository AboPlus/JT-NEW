package com.abo.jt.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abo
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    /*@RequestMapping("/login/doLogin")
    public String doLogin(){
        return "doLogin...";
    }*/

    @GetMapping("/login/{username}/{password}")
    public String doLogin(@PathVariable String username,
                          @PathVariable String password){
        //将账号和密码封装token对象
        //参考官网
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //基于subject对象将token提交给securityManager
        Subject subject = SecurityUtils.getSubject();
        //提交给securityManager
        subject.login(token);
        return "login ok";
    }

    @RequestMapping("logout")
    public String doLogout(){
        return "doLogout...";
    }

    /**
     * @RequiresPermissions("value") 该注解表示访问该方法需要value权限
     */
    @RequiresPermissions("sys:user:create")
    @RequestMapping("doCreate")
    public String doCreate(){
        return "doCreate...";
    }

    @RequiresPermissions("sys:user:delete")
    @RequestMapping("doDelete")
    public String doDelete(){
        return "doDelete...";
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping("doUpdate")
    public String doUpdate(){
        return "doUpdate...";
    }

    @RequiresPermissions("sys:user:retrieve")
    @RequestMapping("doRetrieve")
    public String doRetrieve(){
        return "doRetieve...";
    }




}
