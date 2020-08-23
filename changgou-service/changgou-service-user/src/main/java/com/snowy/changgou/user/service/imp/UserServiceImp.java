package com.snowy.changgou.user.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.user.entity.User;
import com.snowy.changgou.user.mapper.UserMapper;
import com.snowy.changgou.user.service.UserService;
import com.snowy.changgou.user.tool.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author snowy
 * @date 2020/8/21 22:00
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String getUser(String username, String password) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        return Optional.ofNullable(user)
                .filter(u -> u.getPassword().equals(password))
                .map(c -> JwtUtil.getToken(getUseToString(user)))
                .orElse(null);
    }

    private String getUseToString(User user){
        Map<String, String> username = Optional.ofNullable(user).map(u -> {
            Map<String, String> map = new HashMap<>();
            map.put("username", u.getUsername());
            return map;
        }).orElse(new HashMap<>());
        return JSONObject.toJSONString(username);
    }
}
