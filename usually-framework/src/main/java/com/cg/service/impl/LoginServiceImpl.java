package com.cg.service.impl;

import com.alibaba.fastjson.JSON;
import com.cg.config.GlobalException;
import com.cg.entity.LoginUser;
import com.cg.entity.LoginUserDetails;
import com.cg.enums.AppHttpCodeEnum;
import com.cg.mapper.LoginMapper;
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

import java.util.Objects;

/**
 * @author cgJavaAfter
 * @date 2023-05-08 20:37
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;

    //用户的登录
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

    //用户注册
    @Override
    public ResponseResult register(LoginUser user) {

        return null;
    }

    //获取用户详细信息
    @Override
    public ResponseResult GetUserInfo() {
        //获取用户Id，根据用户id查询用户信息并封装成结果
        //TODO 我一直好奇存入holder的数据是怎么保证数据不一样并且能准确取出来的
        LoginUserDetails principal = (LoginUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = principal.getUser().getId();
        LoginUser loginUser = loginMapper.GetLoginById(id);

        LoginUserInfo loginUserInfo = BeanCopyUtils.copyBean(loginUser, LoginUserInfo.class);
        return ResponseResult.okResult(loginUserInfo);
    }
}
