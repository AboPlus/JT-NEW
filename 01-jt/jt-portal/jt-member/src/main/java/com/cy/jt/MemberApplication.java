package com.cy.jt;

import com.cy.jt.common.basic.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类
 * @author Abo
 */
@SpringBootApplication
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
        System.out.println(StringUtils.isEmpty(null));
    }
}
