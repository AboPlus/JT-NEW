package com.abo.jt.config;

import com.abo.jt.service.realm.ShiroRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro配置类
 * @author Abo
 */
@Configuration
public class ShiroConfig {
    @Bean
    public Realm realm(){
        return new ShiroRealm();
    }


    /**
     * 定义过滤规则
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        //配置/user/login/**开头的资源，可以匿名访问(不用登录就可以访问),
        //其中anon为shiro框架指定的匿名过滤器
        chainDefinition.addPathDefinition("/user/login/**", "anon");
        //配置登出操作
        chainDefinition.addPathDefinition("/user/logout","logout");
        //配置以/**开头的资源必须都要经过认证，
        //其中authc为shiro框架指定的认证过滤器
        chainDefinition.addPathDefinition("/**","authc");
        return chainDefinition;
    }


}
