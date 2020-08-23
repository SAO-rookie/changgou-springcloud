package com.snowy.changgou.gateweyone.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * @author snowy
 * @date 2020/8/22 21:40
 */
public class JwtUtil {
    // 过期时间
    private final static long EXPIRE = 1000 * 60 * 60;

    // 秘钥
    private final static String SERCRET = "saber";

    /*
     * @Description：设置token
     * @Author: snowy
     * @Date: 2020/8/22 21:53
     * @Param: User
     * @Return: String
     **/
    public static String getToken(String user){
       return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,generalKey()).compact();
    }

    /*
     * @Description: 得到token并且解密
     * @Author: snowy
     * @Date: 2020/8/22 21:53
     * @Param: String
     * @Return: Claims
     **/
    public static Claims decryptToken(String token)throws Exception{
       return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /*
     * @Description: 对秘钥加密
     * @Author: snowy
     * @Date: 2020/8/22 21:56
     * @Param:
     * @Return: SecretKey
     **/
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtUtil.SERCRET.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }



}
