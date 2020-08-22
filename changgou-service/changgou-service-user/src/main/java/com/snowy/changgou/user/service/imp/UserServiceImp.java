package com.snowy.changgou.user.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.user.entity.User;
import com.snowy.changgou.user.mapper.UserMapper;
import com.snowy.changgou.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author snowy
 * @date 2020/8/21 22:00
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {
}
