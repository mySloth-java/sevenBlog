package com.cg.controller;

import com.cg.entity.User;
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

    @PostMapping("/login")
    public ResponseResult Login(@RequestBody User user){
        return loginService.Login(user);
    }

    @GetMapping("/logout")
    public ResponseResult Logout(){
        return loginService.Logout();
    }

}
