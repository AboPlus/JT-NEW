package com.abo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Abo
 */
public class TimeInteceptor implements HandlerInterceptor {
    /** 此方法是在@Controller对象方法之前执行 */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        LocalDateTime time = LocalDateTime.now();
        /*System.out.println("格式化前的时间为："+time);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = time.format(timeFormatter);
        System.out.println("格式化后的时间为："+format);*/
        int hour = time.getHour();
        System.out.println("hour= " + hour);
        /*if ( hour<6 || hour>23) {
            throw new RuntimeException("请在指定时间访问");
        }*/
        return true; //true表示放行
    }
}
