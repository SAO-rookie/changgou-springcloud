package com.snowy.changgou.goods.service.impl;

import com.snowy.changgou.goods.entity.Category;
import com.snowy.changgou.goods.entity.Template;
import com.snowy.changgou.goods.mapper.CategoryMapper;
import com.snowy.changgou.goods.mapper.TemplateMapper;
import com.snowy.changgou.goods.service.TemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements TemplateService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public Template findByCategoryId(Integer id) {
        Category category = categoryMapper.selectById(id);
        return templateMapper.selectById(category.getTemplateId());
    }
}
