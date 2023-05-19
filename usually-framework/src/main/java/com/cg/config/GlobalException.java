package com.cg.config;

import com.cg.enums.AppHttpCodeEnum;

/**
 * @author cgJavaAfter
 * @date 2023-05-19 14:02
 */
//自定义全局异常处理信息
public class GlobalException extends RuntimeException{
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public GlobalException(AppHttpCodeEnum codeEnum){
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }
}
