package com.example.demo.config.demosecurity.config;

import com.example.demo.config.ConfigProperties;
import com.example.demo.config.demosecurity.hander.FailHandler;
import com.example.demo.config.demosecurity.hander.SuccessHandler;
import com.example.demo.config.demosecurity.filter.JwtLoginFilter;
import com.example.demo.config.demosecurity.userservice.TestUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 适配器
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class DemoSecurItyConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置
     */
    @Autowired
    private ConfigProperties configProperties;

    /**
     * 业务层
     */
    @Autowired
    private TestUserService testUserService;



    @Autowired
    private SuccessHandler successHandler;

    @Autowired
    private  FailHandler failHandler;


    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置处理的业务层和加密方式
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws  Exception {
        //  设置密码 以及密码加密的算法
        auth.userDetailsService(testUserService).passwordEncoder(bCryptPasswordEncoder());

    }

    /**
     * 配置添加
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class);

        // 忽略放行的路径
        //List<String> ignoreUrl = configProperties.getIgnoreUrl();
        // 禁用csrf 因为其默认不放行post
        http.csrf().disable()
                // 禁用session
                .sessionManagement().disable()
                .authorizeRequests()
                // 放行的参数可以进行配置
                //.antMatchers(HttpMethod.GET,ignoreUrl.toArray(new String[ignoreUrl.size()])).permitAll()
                //.antMatchers(HttpMethod.POST,"/testUser").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form->
                {
                    form.loginPage("/mylogin");
                    form.permitAll();
                });


    }


    @Bean
    public JwtLoginFilter jwtLoginFilter() throws Exception {
        JwtLoginFilter jwtLoginFilter= new JwtLoginFilter();
        jwtLoginFilter.setAuthenticationManager(authenticationManager());
        return  jwtLoginFilter;
    }




}
