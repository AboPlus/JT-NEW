package com.abo.jt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Abo
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭跨域攻击 ↓
        http.csrf().disable();
        //设置登录表单
        http.formLogin()
                //自定义登录页面
                .loginPage("/login.html")
                //.usernameParameter("username") 这里的参数名一定要与登录表单中的name属性对应
                //.passwordParameter("password")这里的参数名一定要与登录表单中的name属性对应
                //处理登录请求的url,参数要和form表单中的action属性保持一致
                .loginProcessingUrl("/login")
                //登录成功默认页面,如果不设置默认是/index.html
                .defaultSuccessUrl("/index.html");
                // 配置好后可重定向
                //.successHandler(new RedirectAuthenticationSuccessHandler("https://www.baidu.com"));
        //设置认证规则(哪些资源必须登录后才能访问，哪些资源可以直接访问)
        http.authorizeRequests()
                //匹配到相应的资源后放行，如果有多个资源需要放行，用逗号隔开
                .antMatchers("/login.html", "/images/**").permitAll()
                //所有请求都要认证(除了上面的放行)
                .anyRequest().authenticated();
    }
}
