package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cgJavaAfter
 * @date 2023-05-17 21:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//用户登录完成后的信息封装，包括token和LoginUser信息
public class LoginUserVo {
    private String token;
    private LoginUserInfo loginUserInfo;
}
