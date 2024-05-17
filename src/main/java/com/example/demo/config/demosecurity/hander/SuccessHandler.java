package com.example.demo.config.demosecurity.hander;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功的返回 主要用于前后端分离，直接返回登录失败的信息
 * @author yuongyang
 * @date 2024/5/17 8:33
 * @email 956158720@qq.com
 */
@Component
@Slf4j
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功"+authentication);
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> result = new HashMap<>();
        result.put("data","登录成功");
        response.getWriter().write(JSONObject.toJSONString(result));

    }
}
