package com.cg.controller;

import com.cg.entity.LoginUser;
import com.cg.service.LoginService;
import com.cg.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cgJavaAfter
 * @date 2023-05-08 20:34
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //用户注册
    @PostMapping("/register")
    public ResponseResult Register(@RequestBody LoginUser user){
        return loginService.Register(user);
    }

    @PostMapping("/login")
    public ResponseResult Login(@RequestBody LoginUser user){
        return loginService.Login(user);
    }

    @PostMapping("/logout")
    public ResponseResult Logout(){
        return loginService.Logout();
    }

    @GetMapping("/info")
    public ResponseResult GetUserInfo(){
        return loginService.GetUserInfo();
    }

    @PutMapping
    public ResponseResult Update(@RequestBody LoginUser user){
        return loginService.Update(user);
    }





}
