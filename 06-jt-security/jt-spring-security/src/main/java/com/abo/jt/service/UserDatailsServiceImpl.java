package com.abo.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Abo
 */
@Component
public class UserDatailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.基于用户名从数据库去查询用户信息
        /* 1.1 用户基本信息 */
        if (!"abo".equals(username)) {
            throw new UsernameNotFoundException("user not found");
        }
        /* 1.2 用户权限信息(用户是什么角色，这个角色有什么权限等) */
        //2.封装用户信息并返回
        String password = bCryptPasswordEncoder.encode("abo");
        /* 用户的权限 */
        List<GrantedAuthority> grantedAuthorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList("/doRetrieve,/doCreate");
        return new User(username, password, grantedAuthorities);
    }
}
