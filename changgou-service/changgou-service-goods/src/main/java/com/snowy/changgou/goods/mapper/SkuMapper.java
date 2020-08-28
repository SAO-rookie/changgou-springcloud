package com.snowy.changgou.goods.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.snowy.changgou.goods.entity.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
public interface SkuMapper extends BaseMapper<Sku> {
    int updateById(@Param("skuId")String skuId,@Param("num") Integer num);


}
