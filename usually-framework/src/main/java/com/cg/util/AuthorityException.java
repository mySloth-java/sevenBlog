package com.cg.util;

import com.cg.entity.LoginUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-05-16 15:31
 */
//自定义权限校验方法
@Component("myAuthority")
public class AuthorityException {

    public boolean hasAuthority(String authority){
        //1、从SecurityHolder中获取到权限信息集合
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails principal = (LoginUserDetails) authentication.getPrincipal();
        List<String> permissions = principal.getList();
        //2、判断是否有权限信息
        return permissions.contains(authority);
    }


}
