package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/**
 * @author Administrator
 */
@SpringBootApplication
// 日志打印
@Slf4j
@EnableWebSecurity
// 用于拦截 使用注解校验权限开启
@EnableGlobalMethodSecurity(prePostEnabled=true)
//@MapperScan(value = "com.example.demo.dao")
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
