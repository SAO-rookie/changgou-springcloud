package com.snowy.changgou.goods.service.impl;

import com.snowy.changgou.content.tool.TokenDecode;
import com.snowy.changgou.goods.service.SkuService;
import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.goods.mapper.SkuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.order.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TokenDecode tokenDecode;
    @Autowired
    private  SkuMapper  skuMapper;

    @Override
    public boolean updateNumByid(String skuId, Integer num) {
        return skuMapper.updateById(skuId, num)>0;
    }
}
