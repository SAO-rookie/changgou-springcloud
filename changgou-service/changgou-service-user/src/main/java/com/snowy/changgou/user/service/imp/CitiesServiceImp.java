package com.snowy.changgou.user.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.user.entity.Cities;
import com.snowy.changgou.user.mapper.CitiesMapper;
import com.snowy.changgou.user.service.CitiesService;
import org.springframework.stereotype.Service;

/**
 * @author snowy
 * @date 2020/8/21 21:58
 */
@Service
public class CitiesServiceImp extends ServiceImpl<CitiesMapper, Cities> implements CitiesService {
}
