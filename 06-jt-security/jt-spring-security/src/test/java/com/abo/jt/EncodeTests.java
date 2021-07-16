package com.abo.jt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

@SpringBootTest
public class EncodeTests {

    @Test
    void encode(){
        //实例化Spring-Security提供的加密对象
        //下面的对象是按照BCrypt加密规则执行加密的对象
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //将制定字符串(密码)加密为密文字符串的方法如下
        String password = encoder.encode("abo");
        //每次加密的结果都是不同的,这种现象是随机加盐技术的结果
        System.out.println(password);
        //$2a$10$vMsqvxR47qhaTSGLTKoSOOoZoCTJVajz3By1eIhhdvZkIJglNmZLK
    }

    @Test
    void match(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //下面的方法是验证一个字符串是否匹配一个加密结果的方法
        //参数1:明文密码  参数2:加密后的密码
        //返回值是明文密码是否可能生成参数2的结果
        boolean flag = encoder.matches("abo", "$2a$10$vMsqvxR47qhaTSGLTKoSOOoZoCTJVajz3By1eIhhdvZkIJglNmZLK");
        System.out.println(flag);
    }

    @Test
    void testMd5(){
        String password = "abo";
        String pwdMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(pwdMd5);
    }


}
