package com.example.demo.config.security.exceptionenum;

/**
 * @author yuongyang
 * @date 2024/5/20 19:14
 * @email 956158720@qq.com
 */
public enum ExceptionStaus {

    /**
     * 接口权限不足
     */
    INF_NO_ACCESS(10000,"接口权限不足");

    /**
     * 错误编码
     */
    private   Integer code;

    /**
     * 错误消息
     */
    private  String message;

    ExceptionStaus(Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
