package com.snowy.changgou.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.snowy.changgou.content.tool.Result;
import com.snowy.changgou.content.tool.StatusCode;
import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.goods.service.SkuService;
import com.snowy.changgou.order.entity.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;
    /*
     * @Description
     * @Author: snowy
     * @Date: 2020/8/5 21:28
     * @Param:[status]
     * @Return: com.snowy.changgou.content.tool.Result
     **/
    @GetMapping("/status/{status}")
    public List<Sku> findByStatus(@PathVariable String status){
        return  skuService.list(Wrappers.<Sku>lambdaQuery().eq(Sku::getStatus,status));
    }

    @PostMapping("/cutBack")
    public boolean cutBack(String skuId, Integer num){
      return   skuService.updateNumByid(skuId,num);
    }
    /*
     * @Description
     * @Author: snowy
     * @Date: 2020/8/28 13:58
     * @Param:[id]
     * @Return: com.snowy.changgou.goods.entity.Sku
     **/
    @GetMapping("/{id}")
    public Sku findById(@PathVariable String id){
        return  skuService.getById(id);
    }
}

