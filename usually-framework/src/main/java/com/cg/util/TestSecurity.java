package com.cg.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.cg.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author cgJavaAfter
 * @date 2023-05-06 14:55
 */
@Data
@NoArgsConstructor
public class TestSecurity implements UserDetails {

    private User user;

    //定义成员变量封装权限信息
    private List<String> list;

    public TestSecurity(User user, List<String> list) {
        this.user = user;
        this.list = list;
    }

    @JSONField(serialize = false)//redis并不会让其序列化
    private Set<GrantedAuthority> authorities;//减少每一次调用时集合转换浪费时间

    //权限信息security会调用此方法
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null){
            return authorities;//如果有权限信息就不需要转换
        }

        //将list的权限字符串信息转化为Granted实现类
        authorities = new HashSet<>();
        //TODO 改为函数式编程
        for(String oldList : list){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(oldList);
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
