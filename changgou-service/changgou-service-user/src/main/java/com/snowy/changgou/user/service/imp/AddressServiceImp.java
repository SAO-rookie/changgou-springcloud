package com.snowy.changgou.user.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.user.entity.Address;
import com.snowy.changgou.user.mapper.AddressMapper;
import com.snowy.changgou.user.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * @author snowy
 * @date 2020/8/21 21:56
 */
@Service
public class AddressServiceImp extends ServiceImpl<AddressMapper, Address> implements AddressService {
}
