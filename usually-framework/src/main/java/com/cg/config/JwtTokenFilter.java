package com.cg.config;

import com.alibaba.fastjson.JSON;
import com.cg.entity.LoginUserDetails;
import com.cg.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author cgJavaAfter
 * @date 2023-05-09 09:46
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从header中获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //为空则放行交给下一个过滤器处理，不让其走下面的解析逻辑
            filterChain.doFilter(request, response);
            return;
        }

        //2、解析token
        String id;
        try {
            Claims parseJWT = JwtUtil.parseJWT(token);
            id = parseJWT.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token不合法");
        }

        //3、从redis中根据id获取用户信息
        String userInfo = stringRedisTemplate.opsForValue().get("login:user:" + id);

        //反序列化得到对象
        LoginUserDetails security = JSON.parseObject(userInfo, LoginUserDetails.class);

        //判断是否为空
        if(Objects.isNull(security)){
            throw new RuntimeException("用户未登录");
        }

        //4、存入securityHolder，需要封装对象authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(security, null, security.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}
