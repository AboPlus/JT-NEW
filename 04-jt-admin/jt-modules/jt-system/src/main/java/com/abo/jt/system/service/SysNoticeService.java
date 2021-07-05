package com.abo.jt.system.service;


import com.abo.jt.common.domain.SysNotice;
import com.abo.jt.common.domain.SysNotice;

import java.util.List;

/**
 * 业务逻辑处理
 * @author Abo
 */
public interface SysNoticeService {
    List<SysNotice> selectNotices(SysNotice notice);
    SysNotice selectById(Long id);
    int insertNotice(SysNotice notice);
    int updateNoitce(SysNotice notice);
    int deleteNotice(Long ...id);
}
