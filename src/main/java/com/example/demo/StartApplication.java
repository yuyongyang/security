package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/**
 * @author Administrator
 */
@SpringBootApplication
@Slf4j
@EnableWebSecurity
//@MapperScan(value = "com.example.demo.dao")
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
