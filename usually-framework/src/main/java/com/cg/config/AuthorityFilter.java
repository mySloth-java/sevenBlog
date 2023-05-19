package com.cg.config;

import com.alibaba.fastjson.JSON;
import com.cg.util.ResponseResult;
import com.cg.util.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cg.enums.AppHttpCodeEnum.*;

/**
 * @author cgJavaAfter
 * @date 2023-05-14 09:32
 */
@Component
public class AuthorityFilter implements AuthenticationEntryPoint {

    //认证异常捕获过滤器
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //先将异常信息打印出来
        authException.printStackTrace();

//        ResponseResult result = new ResponseResult(401,"用户认证失败");
        ResponseResult ExceptionResult;
        //对认证异常进行更细化的划分
        if(authException instanceof BadCredentialsException){
            //用户名或密码错误BadCredentialsException
            ExceptionResult = new ResponseResult(PASSWORD_ERROR.getCode(),PASSWORD_ERROR.getMsg());
        }else if(authException instanceof InternalAuthenticationServiceException){
            //未登录InternalAuthenticationServiceException
            ExceptionResult = new ResponseResult(NOT_LOGIN.getCode(), NOT_LOGIN.getMsg());
        }else {
            ExceptionResult = new ResponseResult(IDENTIFY_ERROR.getCode(), IDENTIFY_ERROR.getMsg());
        }
        //调用工具类设置返回的数据类型、状态码等信息
        String json = JSON.toJSONString(ExceptionResult);

        //将错误信息返回给前端
        WebUtils.renderString(response,json);
    }
}
