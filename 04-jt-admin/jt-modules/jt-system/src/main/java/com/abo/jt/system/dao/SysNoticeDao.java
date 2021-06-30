package com.abo.jt.system.dao;

import com.abo.jt.system.domain.SysNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问逻辑接口定义
 * @Mapper描述的接口，系统低层会为该接口产生一个实现类
 * @author Abo
 */
@Mapper
public interface SysNoticeDao {
    /**
     * 基于条件查询公告信息
     * @param notice 基于此对象封装查询参数
     * @return 查询到的公告列表
     */
    List<SysNotice> selectNotices(SysNotice notice);

    /**
     * 基于id执行删除操作
     * @param id 要删除的记录id
     * @return 删除的行数
     */
    int deleteById(Long ...id);

    /**
     * 新增通告信息
     * @param notice
     * @return
     */
    int insertNotice(SysNotice notice);

    /**
     * 修改通告信息
     * @param notice
     * @return
     */
    int updateNotice(SysNotice notice);

    /**
     * 基于id查找notice信息
     * @param id
     * @return
     */
    SysNotice selectById(Integer id);
}
