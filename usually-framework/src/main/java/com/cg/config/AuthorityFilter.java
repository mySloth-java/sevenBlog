package com.cg.config;

import com.alibaba.fastjson.JSON;
import com.cg.util.ResponseResult;
import com.cg.util.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cgJavaAfter
 * @date 2023-05-14 09:32
 */
@Component
public class AuthorityFilter implements AuthenticationEntryPoint {

    //认证异常捕获过滤器
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(401,"用户认证失败");
        //调用工具类设置返回的数据类型、状态码等信息
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
