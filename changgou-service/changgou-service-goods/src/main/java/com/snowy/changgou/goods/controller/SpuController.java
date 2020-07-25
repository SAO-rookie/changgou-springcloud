package com.snowy.changgou.goods.controller;


import com.snowy.changgou.goods.entity.Goods;
import com.snowy.changgou.goods.service.SpuService;
import com.snowy.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  添加商品
     * @Author: snowy
     * @Date: 2020/7/24
     * @Param: [goods]
     * @return: com.snowy.tool.Result
     */
    @PostMapping
    public Result saveGoods(@RequestBody Goods goods){
        return Result.ok(spuService.saveGoods(goods));
    }


    /**
     * 根据spuId查询商品
     * @Author: snowy
     * @Date: 2020/7/24
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/goods/{id}")
    public Result findGoodsById(@PathVariable String id){
        //根据ID查询Goods(SPU+SKU)信息
        return  Result.ok(spuService.getGoods(id));
    }

    /**
     * 修改库存
     * @Author: snowy
     * @Date: 2020/7/24
     * @Param: [goods]
     * @return: com.snowy.tool.Result
     */
    @PutMapping
    public Result updateStock(@RequestBody Goods goods){
        return Result.ok(spuService.updateStock(goods));
    }
}

