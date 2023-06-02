package com.cg.enums;

/**
 * @author cgJavaAfter
 * @date 2023-04-23 16:53
 */
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),

    //登录失败
    PASSWORD_ERROR(401,"用户名或密码错误"),
    NOT_LOGIN(402,"请登录后访问"),
    IDENTIFY_ERROR(403,"用户认证失败"),
    NOT_POWER(405,"权限不足，无法访问"),
    NOT_USER(406,"请填写用户名"),
    USER_EXIST(407,"用户已经存在"),

    //文件
    FILE_TYPE_ERROR(411,"文件格式错误，请上传png或jpg格式"),


    //数据库错误
    DATA_ERROR_UPDATE(420,"更新数据库错误!"),
    DATA_ERROR_ADD(421,"添加数据库出错!"),
    DATA_NULL(422,"数据不能为Null"),



    //全局系统错误(没统计的错误)
    SYSTEM_ERROR(500,"出现错误"),



    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

