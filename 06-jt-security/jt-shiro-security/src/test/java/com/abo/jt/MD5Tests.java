package com.abo.jt;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
public class MD5Tests {
    @Test
    void testMD5(){
        String password ="abo";
        String salt = "ABCD";
        //参数说明：密码，盐值，加密次数
        Md5Hash md5Hash = new Md5Hash(password,salt,3);
        System.out.println(md5Hash);//faa175ed187ecc7b34987f24212484d8
    }
}
