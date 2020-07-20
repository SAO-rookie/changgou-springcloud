package com.snowy.changgou.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.changgou.goods.entity.Para;
import com.snowy.changgou.goods.service.ParaService;
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
@RequestMapping("/para")
public class ParaController {
    @Autowired
    private ParaService paraService;

    //Para分页条件搜索实现
    /**
     * @Author: snowy
     * @Date: 2020/7/20
     * @Param: [page, para]
     * @return: com.snowy.tool.Result
     */
    @PostMapping(value = "/page/condition" )
    public Result findPage(Page page ,Para para){
        return Result.ok(paraService.page(page,Wrappers.query(para)));
    }


     /*//Para分页搜索实现

    @GetMapping(value = "/search/{page}/{size}" )
    public Result findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        PageInfo<Para> pageInfo = paraService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }*/


     //多条件搜索品牌数据无分页
    /**
     * @Author: snowy
     * @Date: 2020/7/20
     * @Param: [para]
     * @return: com.snowy.tool.Result
     */
    @PostMapping(value = "/list" )
    public Result findList(Para para){
        return Result.ok(paraService.list(Wrappers.query(para)));
    }


     //根据ID删除品牌数据
    /**
     * @Author: snowy
     * @Date: 2020/7/20
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        return  Result.ok(paraService.removeById(id));
    }


     //修改Para数据
    /**
     * @Author: snowy
     * @Date: 2020/7/20
     * @Param: [para, id]
     * @return: com.snowy.tool.Result
     */
    @PutMapping
    public Result update(@RequestBody  Para para){
        return  Result.ok(paraService.updateById(para));
    }


    //新增Para数据
    /**
     * @Author: snowy
     * @Date: 2020/7/20
     * @Param: [para]
     * @return: com.snowy.tool.Result
     */
    @PostMapping
    public Result add(@RequestBody   Para para){
        return  Result.ok(paraService.save(para));
    }


     ///根据ID查询Para数据
    /**
     * @Author: snowy
     * @Date: 2020/7/20
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/{id}")
    public Result  findById(@PathVariable Integer id){
        return  Result.ok(paraService.getById(id));
    }


     //查询Para全部数据

    @GetMapping
    public Result findAll(){
        return  Result.ok(paraService.list());
    }

}

