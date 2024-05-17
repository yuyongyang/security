package com.example.demo.config.security.hander;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录失败的处理 主要用于前后端分离，直接返回登录成功的信息
 * @author yuongyang
 * @date 2024/5/17 8:35
 * @email 956158720@qq.com
 */

@Component
@Slf4j
public class FailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       log.info("登录失败"+ exception.getMessage());
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> result = new HashMap<>();
        result.put("data","登录失败");
        response.getWriter().write(JSONObject.toJSONString(result));

    }
}
