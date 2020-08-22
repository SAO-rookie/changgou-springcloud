package com.snowy.changgou.user.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.user.entity.Provinces;
import com.snowy.changgou.user.mapper.ProvincesMapper;
import com.snowy.changgou.user.service.ProvinceService;
import org.springframework.stereotype.Service;

/**
 * @author snowy
 * @date 2020/8/21 21:54
 */
@Service
public class ProvinceServiceImp extends ServiceImpl<ProvincesMapper, Provinces> implements ProvinceService {
}
