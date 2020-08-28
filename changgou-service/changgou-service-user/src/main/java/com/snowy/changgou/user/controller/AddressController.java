package com.snowy.changgou.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.snowy.changgou.content.tool.TokenDecode;
import com.snowy.changgou.user.entity.Address;
import com.snowy.changgou.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author snowy
 * @date 2020/8/21 22:02
 */
@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private TokenDecode tokenDecode;

    @GetMapping("/listAddressByUsername")
    public List<Address> listAddressByUsername(){
       String username = tokenDecode.getUserInfo().get("username");
       return addressService.list(Wrappers.<Address>lambdaQuery().eq(Address::getUsername,username));
    }
}
