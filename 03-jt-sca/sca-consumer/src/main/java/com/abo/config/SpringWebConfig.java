package com.abo.config;


import com.abo.interceptor.TimeInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Abo
 */
@Configuration
public class SpringWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeInteceptor())
                .addPathPatterns("/consumer/*");
    }
}
