package com.snowy.changgou.goods.service.impl;

import com.snowy.changgou.goods.entity.Brand;
import com.snowy.changgou.goods.mapper.BrandMapper;
import com.snowy.changgou.goods.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private  BrandMapper brandMapper;
    @Override
    public List < Brand > findByCategory(Integer categoryid) {
        return brandMapper.findByCategory(categoryid);
    }
}
