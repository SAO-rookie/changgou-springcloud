package com.snowy.changgou.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.entity.Brand;
import com.snowy.changgou.mapper.BrandMapper;
import com.snowy.changgou.service.BrandService;
import org.springframework.stereotype.Service;

/**
 * @auther snowy
 * @date 2020/7/9 - 21:02
 */
@Service
public class BrandServiceImp extends ServiceImpl< BrandMapper, Brand > implements BrandService {
}
