package com.example.demo.config.demosecurity.filter;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 拦截用户及密码
 * @author Administrator
 */
@Slf4j
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = super.attemptAuthentication(request,response);
        return  authentication;
    }


//    /**
//     *
//     * @param request 请求
//     * @param response 行营
//     * @param chain 过滤器链
//     * @param authResult the object returned from the <tt>attemptAuthentication</tt>
//     * method.
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        log.info("登录成功"+ authResult);
//        response.setContentType("application/json;charset=utf-8");
//        Map<String,Object> result = new HashMap<>();
//        result.put("data","登录成功");
//        response.getWriter().write(JSONObject.toJSONString(result));
//
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        log.info("登录失败"+ failed);
//        response.setContentType("application/json;charset=utf-8");
//        Map<String,Object> result = new HashMap<>();
//        result.put("data","登录失败");
//        response.getWriter().write(JSONObject.toJSONString(result));
//
//    }


}
