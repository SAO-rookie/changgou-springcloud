package com.snowy.changgou.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.snowy.changgou.content.tool.BCrypt;
import com.snowy.changgou.content.tool.Result;
import com.snowy.changgou.user.entity.User;
import com.snowy.changgou.user.service.UserService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public Result getUser(String username,String password){
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        boolean present = Optional.ofNullable(user)
                .filter(u -> BCrypt.checkpw(password, u.getPassword()))
                .isPresent();
        if (present){
            return Result.ok(user,"登录成功!");
        }else {
            return Result.failed("账号或者密码错误！");
        }
    }
}
