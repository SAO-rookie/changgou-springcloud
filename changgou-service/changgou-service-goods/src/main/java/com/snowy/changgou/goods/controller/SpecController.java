package com.snowy.changgou.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.changgou.goods.entity.Para;
import com.snowy.changgou.goods.entity.Spec;
import com.snowy.changgou.goods.service.SpecService;
import com.snowy.tool.Result;
import com.snowy.tool.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@Controller
@RequestMapping("/spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    /**
     * 带条件查询带分页
     * @Author: snowy
     * @Date: 2020/7/16
     * @Param: [page, spec]
     * @return: com.snowy.tool.Result
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result getAllPageAndCondition(@RequestBody(required = false) Spec spec, @PathVariable int page, @PathVariable  int size){
        Page < Spec > specPage = new Page <>();
        specPage.setCurrent(page);
        specPage.setSize(size);
        return Result.ok(specService.page(specPage, Wrappers.query(spec)));
    }

    /**
     * 纯分页
     * @Author: snowy
     * @Date: 2020/7/16
     * @Param: [page]
     * @return: com.snowy.tool.Result
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result getAllPage(@PathVariable  int page, @PathVariable  int size){
        Page < Spec > specPage = new Page <>();
        specPage.setCurrent(page);
        specPage.setSize(size);
        return Result.ok(specService.page(specPage));
    }


    /**
     * 多条件查询
     * @Author: snowy
     * @Date: 2020/7/16
     * @Param: [spec]
     * @return: com.snowy.tool.Result
     */
    @PostMapping(value = "/search" )
    public Result getgetAllCondition(Spec spec){
        return Result.ok(specService.list(Wrappers.query(spec)));
    }

    /***
     * 查询Spec全部数据
     * @return
     */
    @GetMapping
    public Result findAll(){
        return Result.ok(specService.list());
    }

    /**
     * 根据id删除
     * @Author: snowy
     * @Date: 2020/7/16
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @DeleteMapping(value = "/{id}" )
    public Result deleteById(@PathVariable Integer id){
        return Result.ok(specService.removeById(id));
    }

    /***
     * 根据ID查询Spec数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable Integer id){
        //根据ID查询
        return  Result.ok(specService.getById(id));
    }
    /**
     * 根据id修改
     * @Author: snowy
     * @Date: 2020/7/16
     * @Param: [spec]
     * @return: com.snowy.tool.Result
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Spec spec){
        return Result.ok(specService.updateById(spec));
    }


}

