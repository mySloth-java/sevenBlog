package com.cg.config;

import com.alibaba.fastjson.JSON;
import com.cg.util.ResponseResult;
import com.cg.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cgJavaAfter
 * @date 2023-05-14 09:39
 */
@Component
public class AuthorityHandlerFilter implements AccessDeniedHandler {

    //权限异常过滤器
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(403, "权限不足，无法访问!");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
