package com.snowy.changgou.auth;

import com.alibaba.fastjson.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @author snowy
 * @date 2020/8/24 20:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthTest {
     @Test
     public void testCreateToken(){
        //证书文件路径
        String key_location="changgou.jks";
        //秘钥库密码
        String key_password="changgou";
        //秘钥密码
        String keypwd = "changgou";
        //秘钥别名
        String alias = "changgou";
        //访问证书路径
        Resource resource = new ClassPathResource(key_location);
        //创建秘钥工厂
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(resource, key_password.toCharArray());
        //读取秘钥对(公钥、私钥)
        KeyPair keyPair = keyFactory.getKeyPair(alias, key_password.toCharArray());
        //获取私钥
        RSAPrivateKey rsaPrivate =  (RSAPrivateKey)keyPair.getPrivate();
        //定义Payload
        Map<String, Object> map = new HashMap<>();
        map.put("id","1");
        map.put("name","snowy");
        map.put("roles","admin");
        //生成Jwt令牌
        Jwt jwt = JwtHelper.encode(JSONObject.toJSONString(map), new RsaSigner(rsaPrivate));
        //取出令牌
        Optional.ofNullable(jwt.getEncoded()).ifPresent(System.out::println);
     }

    @Test
     public void ParseJwtTest(){
         //公钥
         String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsb3skeVcYcakgepF+Lv4ZJdnYVHotvF1/GzIdRVGW95Z1YgEt/yoNNOTywBWbmkUOBGs3RsUvhJMqjvVi3N45ZBGSXf5d2xwTfUnKLjSLQlboudw6Gh2d/MZMaHBkQb+pTkmKcrW2rtpJvnBq3w2vQSYlfrY2dMYfBspcAr2DdhWETBIHGc1CvOJr5bKF9DWNL6/x1Izkl6BYL+EyiFO0n9aMke5xoRMjlggU1BimON+435K99DTMA+D3mEV8KjDRw1Vm9vTG/a3ez64rPPIbeFUU20hYjtR+Yyz1jmr3TpyFzAq3oKGyESVRCpNsUBH25YNk49WaEhD7EIVaJ16JQIDAQAB-----END PUBLIC KEY-----";
         //令牌
         String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6ImFkbWluIiwibmFtZSI6InNub3d5IiwiaWQiOiIxIn0.CXzKRK8mPndBTP1m7aVdymSmbxr5xTtMCT1CEtXPSYj0gdTufkUb7upy9x4YlXPh-NQONGCUka-jFaau3Ws8JR8Ut3KUyukvsIDGT0jvzKADlP62_0oeT_LxM9R6CjAGN0EMKk2nulnL8D8-CckHPsa--_a2OcpebvGzo8AoAOOZXyNWvECJv0sTYoNTJVsWJvmddit2LWXvMADzX_-3p-AqGRLaTW43SjX1FXDkg6s_eViAGMNyhdEeQuPj_1iku3yukmZSjLoQe8HVQaw-uDQwenshmoUkfiSfgfidBFUOJOqCnul1yox4Kv53vgmswIeATkjepGHz-rwJtONbFA";
         Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));
         Optional.ofNullable(jwt.getClaims()).ifPresent(System.out::println);
         Optional.ofNullable(jwt.getEncoded()).ifPresent(System.out::println);
     }



}
