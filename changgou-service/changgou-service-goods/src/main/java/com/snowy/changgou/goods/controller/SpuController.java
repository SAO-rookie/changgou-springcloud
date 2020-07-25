package com.snowy.changgou.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.changgou.goods.entity.Goods;
import com.snowy.changgou.goods.entity.Spu;
import com.snowy.changgou.goods.service.SpuService;
import com.snowy.tool.Result;
import com.snowy.tool.StatusCode;
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

    @GetMapping("/pageCondition")
    public Result findPage(Page page,Spu spu){
        return Result.ok(spuService.page(page,Wrappers.query(spu)));
    }

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

    /**
     * 商品审核
     * @Author: snowy
     * @Date: 2020/7/25
     * @Param: [spuId]
     * @return: com.snowy.tool.Result
     */
    @PutMapping("/audit/{spuId}")
    public Result audit(@PathVariable String spuId){
        return  Result.ok(spuService.goodsListingAndReviewById(spuId));
    }

    /**
     * 商品下架
     * @Author: snowy
     * @Date: 2020/7/25
     * @Param: [spuId]
     * @return: com.snowy.tool.Result
     */
    @PutMapping("/pull/{spuId}")
    public Result pull(@PathVariable String spuId){
        return Result.ok(spuService.goodsPullById(spuId));
    }

    /**
     * 商品上架
     * @Author: snowy
     * @Date: 2020/7/25
     * @Param: [spuId]
     * @return: com.snowy.tool.Result
     */
    @PutMapping("/put/{spuId}")
    public Result goodsOnTheShelf(@PathVariable String spuId){
        return Result.ok(spuService.goodsOnTheShelfById(spuId));
    }

    /**
     * 批量上架
     * @Author: snowy
     * @Date: 2020/7/25
     * @Param: [spuIds]
     * @return: com.snowy.tool.Result
     */
    @PutMapping("/put/many")
    public Result putMany(@RequestBody String[] spuIds){
        return Result.ok(spuService.listPullByIds(spuIds));
    }


    /**
     * 批量上架
     * @Author: snowy
     * @Date: 2020/7/25
     * @Param: [spuIds]
     * @return: com.snowy.tool.Result
     */
    @PutMapping("/pull/many")
    public Result pullMany(@RequestBody String[] spuIds){
        return Result.ok(spuService.listOnTheShelfByIds(spuIds));
    }

    /**
     * 逻辑删除
     * @Author: snowy
     * @Date: 2020/7/25
     * @Param: [spuId]
     * @return: com.snowy.tool.Result
     */
    @DeleteMapping("/logic/delete/{spuId}")
    public Result logicDelete(@PathVariable String spuId){
        return  Result.ok(spuService.removeGoodsById(spuId));
    }

    /**
     * 恢复数据
     * @Author: snowy
     * @Date: 2020/7/25
     * @Param: [spuId]
     * @return: com.snowy.tool.Result
     */
    @PutMapping("/restore/{spuId}")
    public Result restore(@PathVariable String spuId){
        return  Result.ok(spuService.restoreGoodById(spuId));
    }

    /**
     * 删除数据
     * @Author: snowy
     * @Date: 2020/7/25
     * @Param: [spuId]
     * @return: com.snowy.tool.Result
     */
    @DeleteMapping("/{spuId}")
    public  Result deleteById(@PathVariable String spuId){
        return  Result.ok(spuService.remove(Wrappers.< Spu >lambdaQuery()
                .eq(Spu::getId,spuId)
                .eq(Spu::getIsDelete,"1")));
    }
}

