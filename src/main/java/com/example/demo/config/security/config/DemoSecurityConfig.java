package com.example.demo.config.security.config;

import com.example.demo.config.security.hander.FailHandler;
import com.example.demo.config.security.hander.SuccessHandler;
import com.example.demo.config.security.filter.JwtLoginFilter;
import com.example.demo.config.security.userservice.SecurityUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 适配器
 * todo 这里需要处理前后端分离场景和前后端一起打包场景
 * @author Administrator
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 业务层
     */
    @Resource
    private SecurityUserServiceImpl securityUserService;

    /**
     * 登录成功处理器
     */
    @Resource
    private SuccessHandler successHandler;

    /**
     * 登录失败处理器
     */
    @Resource
    private  FailHandler failHandler;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置处理的业务层和加密方式
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws  Exception {
        //  设置密码 以及密码加密的算法
        auth.userDetailsService(securityUserService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 配置添加
     * @param http the {@link HttpSecurity} to modify
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        // 禁用csrf 因为其默认不放行post
        http.csrf().disable()
                // 禁用session
                .sessionManagement().disable()
                // 授权接口
                .authorizeRequests()
                // 这里需要放行登录的静态问价地址
                .antMatchers("/", "/static","/css/**","/js/**","/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form->
                {
                    // 指定登录的路由地址
                    form.loginPage("/login");
                    form.permitAll();

                }).logout(
                        out->{
                            out.logoutSuccessHandler(new LogoutSuccessHandler() {
                                @Override
                                public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                   log.info("The User " + authentication.getName() + " has logged out");
                                    UrlPathHelper helper = new UrlPathHelper();
                                    String context = helper.getContextPath(request);
                                    response.sendRedirect(context + "/mylogin");
                                }
                            }
                                ).permitAll();
                        }
                );


    }


    /**
     * 自定义用户密码处理方式
     * @return 过滤器
     * @throws Exception 异常
     */
    @Bean
    public JwtLoginFilter jwtLoginFilter() throws Exception {
        JwtLoginFilter jwtLoginFilter= new JwtLoginFilter();
        jwtLoginFilter.setAuthenticationManager(authenticationManager());
        jwtLoginFilter.setAuthenticationSuccessHandler(successHandler);
        jwtLoginFilter.setAuthenticationFailureHandler(failHandler);
        return  jwtLoginFilter;
    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 支持请求方式
        config.addAllowedOriginPattern("*");
        // 支持跨域
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","OPTIONS"));
        // cookie
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        // 允许请求头信息
        config.addExposedHeader("*");
        // 暴露的头部信息
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 添加地址映射
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }



}
