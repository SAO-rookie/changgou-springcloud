package com.snowy.changgou.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.changgou.goods.service.BrandService;
import com.snowy.changgou.goods.entity.Brand;
import com.snowy.changgou.content.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther snowy
 * @date 2020/7/9 - 22:24
 */
@RestController
    @RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询所有
     * @Author: snowy
     * @Date: 2020/7/10
     * @Param: []
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/page")
    public Result getAll(Page < Brand > page){
        return Result.ok(brandService.page(page));
    }

    /**
     * 根据id查询
     * @Author: snowy
     * @Date: 2020/7/10
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        return Result.ok(brandService.getById(id));
    }

    /**
     * 保存品牌
     * @Author: snowy
     * @Date: 2020/7/10
     * @Param: [brand]
     * @return: com.snowy.tool.Result
     */
    @PostMapping
    public Result save(@RequestBody Brand brand){
        return Result.ok(brandService.save(brand));
    }

    /**
     * 修改品牌
     * @Author: snowy
     * @Date: 2020/7/10
     * @Param: [brand]
     * @return: com.snowy.tool.Result
     */
    @PutMapping
    public Result updateById(@RequestBody Brand brand){
        return Result.ok(brandService.updateById(brand));
    }

    /**
     * 删除品牌
     * @Author: snowy
     * @Date: 2020/7/10
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Integer id){
        return Result.ok(brandService.removeById(id));
    }

    /***
     * 根据分类实现品牌列表查询
     * /brand/category/{id}  分类ID
     */
    @GetMapping(value = "/category/{categoryId}")
    public Result findBrandByCategory(@PathVariable(value = "categoryId")Integer categoryId){
        //调用Service查询品牌数据
        List<Brand> categoryList = brandService.findByCategory(categoryId);
        return  Result.ok(categoryList);
    }

}
