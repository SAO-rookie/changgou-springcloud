package com.snowy.changgou.user.feign;

import com.snowy.changgou.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author snowy
 * @date 2020/8/23 14:59
 */
@FeignClient(name = "changgou-user")
@RequestMapping("/user")
public interface UserFeign {
    @PostMapping("/getUserByUsername")
    User getUserByUsername(String username);

}
