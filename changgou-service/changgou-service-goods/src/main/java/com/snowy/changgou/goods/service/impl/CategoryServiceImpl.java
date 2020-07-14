package com.snowy.changgou.goods.service.impl;

import com.snowy.changgou.goods.entity.Category;
import com.snowy.changgou.goods.mapper.CategoryMapper;
import com.snowy.changgou.goods.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
