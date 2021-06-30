package com.abo.jt.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告实体对象，与表中字段有对应关系，可以基于此对象存储从数据库查询到的数据。
 * *注：Java中所有用于存储数据的对象都让它实现Serializable接口并且添加序列化id
 * @author Abo
 */
@Data
@Accessors(chain = true)
public class SysNotice implements Serializable {
    private static final long serialVersionUID = 56306541077632407L;
    private Integer id;
    private String title;
    private String type;
    private String content;
    private String status;
    private String remark;
    private String createdUser;
    private String modifiedUser;
    private Date createdTime;
    private Date modifiedTime;
}
