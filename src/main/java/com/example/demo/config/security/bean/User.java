package com.example.demo.config.security.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户
 */
public class User implements UserDetails {

    /**
     * 用户名
     */
    private  String username;

    /**
     * 密码
     */
    private String password;

    /**
    权限列表
     */
    private Collection<? extends GrantedAuthority>  authorities;



    public void setAuthorities(Collection<? extends GrantedAuthority> list) {
         this.authorities = list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    // 账号是否超时
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 身份是否超时
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账号是否被停用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
