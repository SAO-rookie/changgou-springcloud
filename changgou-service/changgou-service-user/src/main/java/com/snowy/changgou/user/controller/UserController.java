package com.snowy.changgou.user.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.snowy.changgou.content.tool.BCrypt;
import com.snowy.changgou.content.tool.Result;
import com.snowy.changgou.user.entity.User;
import com.snowy.changgou.user.service.UserService;
import com.snowy.changgou.user.tool.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author snowy
 * @date 2020/8/21 22:01
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Result getUser(String username, String password, HttpServletResponse response){
        String jwt = userService.getUser(username, password);
        if (StrUtil.isNotEmpty(jwt)){
            response.addCookie(new Cookie("Authorization",jwt));
            response.setHeader("Authorization",jwt);
            return Result.ok(jwt,"登录成功!");
        }else {
            return  Result.failed("账号或者密码错误！");
        }
    }
    @GetMapping("/findAllHeader")
    public Result findAllHeader(HttpServletRequest request){
        //Claims claims = Optional.ofNullable(request.getHeader("Authorization")).map(a -> JwtUtil.decryptToken(a)).orElse(null);
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return Result.ok(authorization,"登录成功!");
    }

}
