package com.example.demo.config.security.utils;


import com.alibaba.fastjson2.JSONObject;
import com.example.demo.config.security.bean.Authority;
import com.example.demo.config.security.bean.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * JWT工具类
 * @author Administrator
 */
public class JwtUtil {

    /**
     * 有效期为 生成的token有效时间 60 * 60 *1000 一个小时
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 设置秘钥明文
     */
    public static final String JWT_KEY = "GAGAHSVGVS";

    /**
     * 创建token
     *
     * @param id 用户id
     * @param subject  用户除密码外的名称
     * @param ttlMillis 有效时间
     * @return token
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                //唯一的ID
                .setId(id)
                // 主题 可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("yu")
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签 名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                // 设置过期时间
                .setExpiration(expDate);
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析 token
     *
     * @param jwt token
     * @return 用户信息
     */
    public static Claims parseJWT(String jwt)  {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


    public static void main(String[] args) {
//        User user = new User();
//        user.setUsername("俞永阳");
//        user.setAuthorities(List.of(new Authority("root")));
//        System.out.println(JSONObject.toJSONString(user));
//        System.out.println(createJWT(user.getUsername(), JSONObject.toJSONString(user),null));

        Claims claims=  parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3YzkyYWU5MC1hMjgyLTRiYzktODlhMS0yOTM4NDNkZTUyZDYiLCJzdWIiOiJ7XCJhdXRoZW50aWNhdGVkXCI6dHJ1ZSxcImF1dGhvcml0aWVzXCI6W10sXCJkZXRhaWxzXCI6e1wicmVtb3RlQWRkcmVzc1wiOlwiMDowOjA6MDowOjA6MDoxXCJ9LFwibmFtZVwiOlwiM1wiLFwicHJpbmNpcGFsXCI6e1wiYWNjb3VudE5vbkV4cGlyZWRcIjp0cnVlLFwiYWNjb3VudE5vbkxvY2tlZFwiOnRydWUsXCJjcmVkZW50aWFsc05vbkV4cGlyZWRcIjp0cnVlLFwiZW5hYmxlZFwiOnRydWUsXCJwYXNzd29yZFwiOlwiJDJhJDEwJDB1TENCb21ZUjZ1UjhnU0g3YWVsY3V3eDhtWEJNQXY5YkZaZ1Z5ZkcxVGZScmZrbWg3UzJtXCIsXCJ1c2VybmFtZVwiOlwiM1wifX0iLCJpc3MiOiJ5dSIsImlhdCI6MTcxNTkzNzkyNSwiZXhwIjoxNzE1OTQxNTI1fQ.1aOdYEKb2Whp-MCoROpakp-E4QRdsY-IP0WeknvwXV4");
        System.out.println(claims);
    }
}
