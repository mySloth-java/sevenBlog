package com.cg.config;

import com.cg.enums.AppHttpCodeEnum;
import com.cg.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author cgJavaAfter
 * @date 2023-05-19 14:36
 */

@RestControllerAdvice//ControllerAdvice和body，controller任何异常都会在此进行处理
@Slf4j
//将自定义异常信息响应给前端
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)//处理指定异常抛出
    public ResponseResult SystemExceptionHandler(GlobalException e){
        //打印异常信息
        log.error("出现异常!{}",e);

        //将异常信息封装
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)//处理指定异常抛出
    public ResponseResult ExceptionHandler(Exception e){
        //打印异常信息
        log.error("出现异常!{}",e);

        //将异常信息封装
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,e.getMessage());
    }



}
