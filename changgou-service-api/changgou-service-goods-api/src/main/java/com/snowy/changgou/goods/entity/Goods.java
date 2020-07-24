package com.snowy.changgou.goods.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther snowy
 * @date 2020/7/24 - 20:38
 */
@Data
public class Goods implements Serializable {
    //SPU
    private Spu spu;
    //SKU集合
    private List <Sku> skuList;
}
