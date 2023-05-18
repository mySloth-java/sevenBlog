package com.cg.service;

import com.cg.entity.LoginUser;
import com.cg.entity.LoginUserDetails;
import com.cg.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-05-05 17:32
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private LoginMapper loginMapper;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    //实现用户加密
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = loginMapper.GetLoginByName(username);
        if(user == null){
            throw new RuntimeException("用户密码错误");
        }
        //查询完成后封装成UserDetails返回
//        String elephant = passwordEncoder.encode("elephant");

        //TODO 连表和分段根据需要取舍
        //获取用户Id，根据Id查询roleId，根据roleId找到menuId，进而找到menu表中的perms权限信息
        List<String> authorities = loginMapper.GetAuthority(user.getId());

        return new LoginUserDetails(user,authorities);
    }
}
