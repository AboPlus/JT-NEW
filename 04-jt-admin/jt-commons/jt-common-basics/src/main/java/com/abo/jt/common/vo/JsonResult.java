package com.abo.jt.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Abo
 */
@Data
@Accessors(chain = true)
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -4026141437059909275L;
    /** 响应状态码  1表示正常，0表示错误 */
    private int code=1;
    /** 具体消息 */
    private String message="ok";
    /** 一般对应查询结果,可以将查询结果赋值给此属性 */
    private Object data;

    public JsonResult(){}
    public JsonResult(String message){
        this.message = message;
    }
    public JsonResult(Object data){
        this.data = data;
    }
    public JsonResult(Throwable e){
        this.code=0;
        this.message=e.getMessage();
    }




}
