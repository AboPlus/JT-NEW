package com.abo.jt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 假如登录成功以后需要重定向到其它的域名地址可以这样进行实现
 * @author Abo
 */
public class RedirectAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * redirectUrl之后接收的是需要重定向的目标地址，通过构造方法给成员变量赋值，在重写方法中调用重定向地址。
     */
    private final String redirectUrl;
    public RedirectAuthenticationSuccessHandler(String redirectUrl){
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.sendRedirect(redirectUrl);
    }
}
