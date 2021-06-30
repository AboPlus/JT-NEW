package com.abo.jt.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@SpringBootTest
public class DataSourceTests {

    // @Slf4j和下面这行代码只需要一个就可以了，@Slf4j低层会自动生成下面这行代码
    //private static final Logger log = LoggerFactory.getLogger(DataSourceTests.class);

    @Autowired
    private DataSource dataSource;  //此对象在springboot启动时已自动配置

    @Test
    void testGetConnection() throws Exception{
        Connection conn = dataSource.getConnection();
        //System.out.println("connection= " + conn);
        log.debug("connection is {}",conn);
    }

}
