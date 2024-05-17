package com.example.demo.config.demosecurity.userservice;

import com.example.demo.config.demosecurity.bean.Authority;
import com.example.demo.config.demosecurity.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class TestUserService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("当前登录用户：{}",username);
        // 数据库查找
        User user= new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(username));
        List<Authority> list=new ArrayList<>();
        list.add(new Authority("root"));
        return user;
    }
}
