package com.snowy.changgou.goods.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.goods.entity.Brand;
import com.snowy.changgou.goods.mapper.BrandMapper;
import com.snowy.changgou.goods.service.BrandService;
import org.springframework.stereotype.Service;

/**
 * @auther snowy
 * @date 2020/7/9 - 21:02
 */
@Service
public class BrandServiceImp extends ServiceImpl< BrandMapper, Brand > implements BrandService {
}
