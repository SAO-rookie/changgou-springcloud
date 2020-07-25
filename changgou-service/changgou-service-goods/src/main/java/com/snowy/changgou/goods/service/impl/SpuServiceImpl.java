package com.snowy.changgou.goods.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.snowy.changgou.goods.entity.*;
import com.snowy.changgou.goods.mapper.BrandMapper;
import com.snowy.changgou.goods.mapper.CategoryMapper;
import com.snowy.changgou.goods.mapper.SkuMapper;
import com.snowy.changgou.goods.mapper.SpuMapper;
import com.snowy.changgou.goods.service.SpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.tool.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public boolean saveGoods(Goods goods) {
        Spu spu = goods.getSpu();
        // 修改或增加Spu
        if (StrUtil.isEmpty(spu.getId())){
            // 增加
            spu.setId(String.valueOf(idWorker.nextId()));
            spuMapper.insert(spu);
        }else {
            // 修改
            spuMapper.updateById(spu);
            QueryWrapper < Sku > wrapper = new QueryWrapper <>();
            skuMapper.delete(wrapper.lambda().eq(Sku::getSpuId,spu.getId()));
        }
        //增加Sku
        Category category = categoryMapper.selectById(spu.getCategory3Id());
        Brand brand = brandMapper.selectById(spu.getBrandId());

        List < Sku > skuList = goods.getSkuList();
        skuList.forEach(s->{
            if (StrUtil.isEmpty(s.getSpec())){
                s.setSpec("{}");
            }
            String name = spu.getName();

            Map<String,String> specMap = JSON.parseObject(s.getSpec(), Map.class);

            //循环组装Sku的名字
            for (String key : specMap.keySet()) {
                // 获取SPU的名称 拼接 即可
                name += " " + specMap.get(key);
            }
            s.setId(String.valueOf(idWorker.nextId()));
            s.setName(name);
            s.setSpuId(spu.getId());
            s.setCreateTime(LocalDateTime.now());
            s.setUpdateTime(LocalDateTime.now());
            s.setCategoryId(spu.getCategory3Id());
            s.setCategoryName(category.getName());
            s.setBrandName(brand.getName());
            skuMapper.insert(s);
        });
        return true;
    }

    @Override
    public Goods getGoods(String spuId) {
        Spu spu = spuMapper.selectById(spuId);
        QueryWrapper < Sku > wrapper = new QueryWrapper <>();
        List < Sku > skus = skuMapper.selectList(wrapper.lambda().eq(Sku::getSpuId, spuId));
        return Goods.getInstance(spu,skus);
    }

    @Override
    public boolean updateStock(Goods goods) {
        List < Sku > skuList = goods.getSkuList();
        QueryWrapper < Sku > wrapper = new QueryWrapper <>();
        skuList.forEach(s->{
            skuMapper.update(s, wrapper.lambda().select(Sku::getNum).eq(Sku::getSpuId,goods.getSpu().getId()));
        });
        return true;
    }


}
