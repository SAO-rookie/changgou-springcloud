package com.snowy.changgou.goods.service;

import com.snowy.changgou.goods.entity.Goods;
import com.snowy.changgou.goods.entity.Spu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
public interface SpuService extends IService<Spu> {
    boolean saveGoods(Goods goods);
    
    Goods getGoods(String spuId);

    boolean updateStock(Goods goods);

    boolean goodsListingAndReviewById(String spuId);

    boolean goodsPullById(String spuId);

    boolean goodsOnTheShelfById(String spuId);

    boolean listPullByIds(String[] spuIds);

    boolean listOnTheShelfByIds(String[] spuIds);

    boolean removeGoodsById(String spuId);

    boolean restoreGoodById(String spuId);
}
