package com.example.demo.config.security.bean;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    /**
     * 用户角色字段
     */
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String str){
        this.authority=str;
    }

    public  Authority(String str){
        this.authority=str;
    }

    public Authority() {
    }

}
