package com.abo.jt.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring Security 异常处理类
 *      1.认证异常
 *      2.授权异常
 * 配置好之后在配置类(Xxx impl WebSecurityConfigurerAdapter)中通过http.exceptionHandling(). ... 调用
 * @author Abo
 */
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    /**
     * 认证异常处理方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("uri", httpServletRequest.getRequestURI());
        map.put("code",HttpServletResponse.SC_UNAUTHORIZED);
        map.put("msg", "认证失败,请先登录");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //设置响应数据的编码(给自己看)
        httpServletResponse.setCharacterEncoding("UTF-8");
        //告诉浏览器要以什么形式响应内容
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(map);
        PrintWriter out = httpServletResponse.getWriter();
        out.println(responseBody);
        out.flush();
        out.close();
    }

    /**
     * 授权异常处理方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("uri", httpServletRequest.getRequestURI());
        map.put("code", HttpServletResponse.SC_FORBIDDEN);
        map.put("msg", "没有此资源的访问权限");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(map);
        PrintWriter out = httpServletResponse.getWriter();
        out.println(responseBody);
        out.flush();
        out.close();
    }
}
