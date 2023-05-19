package com.cg.service.impl;

import com.alibaba.fastjson.JSON;
import com.cg.config.GlobalException;
import com.cg.entity.LoginUser;
import com.cg.entity.LoginUserDetails;
import com.cg.enums.AppHttpCodeEnum;
import com.cg.service.LoginService;
import com.cg.util.BeanCopyUtils;
import com.cg.util.JwtUtil;
import com.cg.util.ResponseResult;
import com.cg.vo.LoginUserInfo;
import com.cg.vo.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public ResponseResult Login(LoginUser user) {
        //extra 添加全局异常信息
        if(!StringUtils.hasText(user.getUserName())){
            //没有传入用户名给出指定错误提示，前端同样会做处理
            throw new GlobalException(AppHttpCodeEnum.NOT_USER);
        }


        //1、注入AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //2、将Authentication结果封装给providerManager过滤器完成验证操作，它就会调用继承UserDetails接口的实现类方法完成重写
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断，如果不为空则在数据可以查到 TODO 此处没必要再加判断吧，待测试
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录错误");
        }

        //3、认证成功后，根据authenticate的信息得到userId将其生成为jwt存入返回值中
        LoginUserDetails principal = (LoginUserDetails) authenticate.getPrincipal();
        String id = principal.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);

        //采用自定义vo封装user信息和token信息
        //3.1将user的信息拷贝封装vo
        LoginUserInfo loginUserInfo = BeanCopyUtils.copyBean(principal.getUser(), LoginUserInfo.class);

        //3.2将token和userInfo存入
        LoginUserVo loginUserVo = new LoginUserVo(jwt,loginUserInfo);

//        HashMap<Object, Object> map = new HashMap<>();
//        map.put("token",jwt);

        //将完整信息序列化
        String userInfo = JSON.toJSONString(principal);

        //4、完整信息存入redis中
        stringRedisTemplate.opsForValue().set("login:user:"+id,userInfo);
        return ResponseResult.okResult(loginUserVo);
    }

    //退出登录
    @Override
    public ResponseResult Logout() {
        //获取securityHolder的信息，通过信息得到id删除redis内的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails principal = (LoginUserDetails)authentication.getPrincipal();
        Long id = principal.getUser().getId();
        stringRedisTemplate.delete("login:user:"+id);
        return ResponseResult.okResult();
    }
}
