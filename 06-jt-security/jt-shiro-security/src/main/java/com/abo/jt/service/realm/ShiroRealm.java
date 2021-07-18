package com.abo.jt.service.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Abo
 */
public class ShiroRealm extends AuthorizingRealm {
    /**
     * 此方法负责获取并封装授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取登录用户(登录时传入的用户身份是谁)
        //2.基于登录用户id获取用户权限标识(后续从数据库获取)
        Set<String> stringPerimissions = new HashSet<>();
        stringPerimissions.add("sys:user:retrieve");
        stringPerimissions.add("sys:user:create");
        //3.封装数据并返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(stringPerimissions);
        return info;
    }

    /**
     * 此方法负责获取并封装认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户提交的认证用户信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //2.基于用户名查询从数据库用户信息
        //3.判断用户是否存在
        if (!"abo".equals(username)){
            //账户不存在
            throw new UnknownAccountException();
        }
        //4.封装认证信息并返回
        String password = "abo";
        String salt = "ABCD";
        //参数说明：密码，盐值，加密次数
        Md5Hash md5Hash = new Md5Hash(password,salt,3);
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        //密码
        String hashedPassword=md5Hash.toString();
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(
                        //principal 传入的用户身份
                        username,
                        //hashedCredentials
                        hashedPassword,
                        //credentialsSalt
                        credentialsSalt,
                        //realmName
                        getName());
        return info;
    }

    /**
     * 获取凭证加密算法
     * @return
     */
    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(3);
        return matcher;
    }

}
