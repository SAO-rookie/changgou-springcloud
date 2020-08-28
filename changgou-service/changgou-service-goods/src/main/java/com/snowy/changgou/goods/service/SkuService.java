package com.snowy.changgou.goods.service;

import com.snowy.changgou.goods.entity.Sku;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.changgou.order.entity.OrderItem;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
public interface SkuService extends IService<Sku> {
    boolean updateNumByid(String skuId, Integer num);
}
