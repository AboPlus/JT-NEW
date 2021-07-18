package com.abo.jt.util;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author Abo
 */
@RestControllerAdvice
public class ShiroExceptionHandler {

    @ExceptionHandler(ShiroException.class)
    public String doShiroException(ShiroException e){
        if(e instanceof UnknownAccountException){
            return "用户名不存在";
        }else if(e instanceof IncorrectCredentialsException){
            return "密码不正确";
        }else if(e instanceof LockedAccountException){
            return "账户被锁定";
        }else if(e instanceof AuthorizationException){
            return "没有权限";
        }else{
            return "认证或授权失败";
        }
    }


}
