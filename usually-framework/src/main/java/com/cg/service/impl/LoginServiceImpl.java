package com.cg.service.impl;

import com.alibaba.fastjson.JSON;
import com.cg.entity.User;
import com.cg.service.LoginService;
import com.cg.util.JwtUtil;
import com.cg.util.ResponseResult;
import com.cg.util.TestSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author cgJavaAfter
 * @date 2023-05-08 20:37
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult Login(User user) {
        //1、注入AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword());
        //2、将Authentication结果封装给providerManager过滤器完成验证操作，它就会调用继承UserDetails接口的实现类方法完成重写
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断，如果不为空则在数据可以查到
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录错误");
        }

        //3、认证成功后，根据authenticate的信息得到userId将其生成为jwt存入返回值中
        TestSecurity principal = (TestSecurity) authenticate.getPrincipal();
        String id = principal.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token",jwt);

        //将完整信息序列化
        String userInfo = JSON.toJSONString(principal);

        //4、完整信息存入redis中
        stringRedisTemplate.opsForValue().set("login:user:"+id,userInfo);
        return ResponseResult.okResult(map);
    }

    //退出登录
    @Override
    public ResponseResult Logout() {
        //获取securityHolder的信息，通过信息得到id删除redis内的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TestSecurity principal = (TestSecurity)authentication.getPrincipal();
        Integer id = principal.getUser().getId();
        stringRedisTemplate.delete("login:user:"+id);
        return ResponseResult.okResult();
    }
}
