package com.snowy.changgou.goods.service;

import com.snowy.changgou.goods.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
public interface BrandService extends IService<Brand> {
    List <Brand> findByCategory(Integer categoryid);
}
