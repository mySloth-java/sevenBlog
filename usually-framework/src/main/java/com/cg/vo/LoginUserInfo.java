package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author cgJavaAfter
 * @date 2023-05-17 21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//登录用户信息返回类
public class LoginUserInfo {
    //主键
    private Long id;
    //昵称
    private String nickName;
    //邮箱
    private String email;
    private String sex;
    //头像
    private String avatar;
}
