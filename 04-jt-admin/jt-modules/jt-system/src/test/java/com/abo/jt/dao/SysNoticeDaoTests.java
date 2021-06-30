package com.abo.jt.dao;

import com.abo.jt.system.dao.SysNoticeDao;
import com.abo.jt.system.domain.SysNotice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@Slf4j
@SpringBootTest
public class SysNoticeDaoTests {
    @Autowired
    private SysNoticeDao sysNoticeDao;

    @Test
    void testSelectNotices(){
        SysNotice sysNotice = new SysNotice();
        List<SysNotice> noticeList = sysNoticeDao.selectNotices(sysNotice);
        System.out.println(noticeList.size());
        /*for (SysNotice n:noticeList){
            System.out.println(n);
        }*/
        // jdk8 提供的lamda表达式
        noticeList.forEach(item-> System.out.println(item));
    }

    @Test
    void testSelectById(){
        SysNotice notice = sysNoticeDao.selectById(1L);
        log.debug("notice id is {}",notice);
    }

    @Test
    void testDeleteById(){
        int rows = sysNoticeDao.deleteById(40L, 41L);
        log.debug("delete rows is {}",rows);
    }

}
