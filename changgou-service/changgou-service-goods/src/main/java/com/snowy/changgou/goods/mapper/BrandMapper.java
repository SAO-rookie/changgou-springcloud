package com.snowy.changgou.goods.mapper;

import com.snowy.changgou.goods.entity.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 品牌表 Mapper 接口
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
public interface BrandMapper extends BaseMapper < Brand > {
    List < Brand > findByCategory(@Param("categoryid") Integer categoryid);

}
