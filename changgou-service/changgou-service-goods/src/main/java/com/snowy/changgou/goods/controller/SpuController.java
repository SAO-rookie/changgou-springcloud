package com.snowy.changgou.goods.controller;


import com.snowy.changgou.goods.entity.Goods;
import com.snowy.changgou.goods.service.SpuService;
import com.snowy.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuService spuService;

    @PostMapping
    public Result saveGoods(@RequestBody Goods goods){
        return Result.ok(spuService.saveGoods(goods));
    }
}

