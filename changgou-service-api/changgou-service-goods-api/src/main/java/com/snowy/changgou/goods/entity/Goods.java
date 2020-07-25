package com.snowy.changgou.goods.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @auther snowy
 * @date 2020/7/24 - 20:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    private static final long serialVersionUID=1L;
    //SPU
    private Spu spu;
    //SKU集合
    private List <Sku> skuList;
    public static Goods getInstance(Spu spu,List <Sku> skuList){
        return new Goods(spu,skuList);
    }
}
