package com.cg.service;

import com.cg.entity.LoginUser;
import com.cg.util.ResponseResult;

/**
 * @author cgJavaAfter
 * @date 2023-05-08 20:37
 */
public interface LoginService {
    ResponseResult Login(LoginUser user);

    ResponseResult Logout();

    ResponseResult GetUserInfo();

    ResponseResult Update(LoginUser user);

    ResponseResult Register(LoginUser user);
}
