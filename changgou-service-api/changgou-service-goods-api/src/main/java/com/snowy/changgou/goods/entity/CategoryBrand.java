package com.snowy.changgou.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@TableName("tb_category_brand")
@Data
public class CategoryBrand implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 分类ID
     */
      @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 品牌ID
     */
    private Integer brandId;

}
