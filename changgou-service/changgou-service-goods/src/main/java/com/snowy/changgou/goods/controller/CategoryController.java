package com.snowy.changgou.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.changgou.goods.entity.Category;
import com.snowy.changgou.goods.service.CategoryService;
import com.snowy.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /** 分体带条件
     * @Author: snowy
     * @Date: 2020/7/20
     * @Param: [page, category]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/pageCondition")
    public Result findPage(Page page, Category category){
        return Result.ok(categoryService.page(page, Wrappers.query(category)));
    }

    @GetMapping(value = "/page" )
    public Result findPage(Page page){
        return Result.ok(categoryService.page(page));
    }

    @PostMapping(value = "/listCondition" )
    public Result findList(  Category category){
        return Result.ok(categoryService.list(Wrappers.query(category)));
    }

    @PostMapping(value = "/list" )
    public Result findAll(){
        return Result.ok(categoryService.list());
    }

    @GetMapping(value ="/list/{pid}")
    public Result findByPrantId(@PathVariable(value = "pid")Integer pid){
        QueryWrapper < Category > wrapper = new QueryWrapper <>();
        wrapper.eq("",pid);
        return Result.ok(categoryService.list(wrapper));
    }
    @PutMapping
    public Result update(@RequestBody  Category category) {
        return Result.ok(categoryService.updateById(category));
    }

    @PostMapping
    public Result add(@RequestBody   Category category){
        return Result.ok(categoryService.save(category));
    }

}

