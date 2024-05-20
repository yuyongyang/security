package com.example.demo.config.security.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 集中错误处理类
 * @author yuongyang
 * @date 2024/5/20 19:01
 * @email 956158720@qq.com
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    /**
     * 权限不足拦截
     * @param request 请求
     * @param exception 异常
     * @return 返回
     */
    @ExceptionHandler(AccessDeniedException.class)
    public @ResponseBody  Object loginException(HttpServletRequest request, Exception exception){
        log.error(exception.getMessage());
        return "接口权限不足"+request.getRequestURI();    }
}
