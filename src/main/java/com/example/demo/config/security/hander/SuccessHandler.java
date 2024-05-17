package com.example.demo.config.security.hander;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.config.security.utils.JwtUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
           //loginuccessHandlerSingleModel( request,  response,  authentication);
           loginuccessHandlerSingleSplit(request,  response,  authentication);
    }

    /**
     * 前后端一体打包的登录成功的结果
     *
     */
    private void loginuccessHandlerSingleModel(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登录成功"+authentication);
        log.info("The User " + authentication.getName() + " has logg in");
        UrlPathHelper helper = new UrlPathHelper();
        String context = helper.getContextPath(request);
        response.sendRedirect(context + "/");
    }


    /**
     * 前后端分离打包的登录成功的结果
     * 需要去除密码输出到前端 加密后的也不行
     */
    private void loginuccessHandlerSingleSplit(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登录成功"+authentication);
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> result = new HashMap<>();
        result.put("data","登录失败");
        String uuid = UUID.randomUUID().toString();
        String token = JwtUtil.createJWT(uuid,JSONObject.toJSONString(authentication) ,null);
        result.put("token",token);
        response.getWriter().write(JSONObject.toJSONString(result));
        response.setHeader("token", token);

    }

}
