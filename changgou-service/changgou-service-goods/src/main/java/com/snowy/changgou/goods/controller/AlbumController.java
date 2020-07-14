package com.snowy.changgou.goods.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.changgou.goods.entity.Album;
import com.snowy.changgou.goods.service.AlbumService;
import com.snowy.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther snowy
 * @date 2020/7/13 - 22:10
 */
@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    /**
     * 分页加条件
     * @Author: snowy
     * @Date: 2020/7/13
     * @Param: [page]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/page")
    public Result getAllPage(Page< Album > page,@RequestBody(required = false) Album album){
        return Result.ok(albumService.page(page, Wrappers.query(album)));
    }


    /**
     * 查询所有不带分页待条件
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: []
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/getAllCondition")
    public Result getAllCondition(@RequestBody(required = false) Album album){
        return Result.ok(albumService.list(Wrappers.query(album)));
    }

    /**
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: []
     * @return: com.snowy.tool.Result
     */
    @GetMapping
    public Result getAll(){
        return Result.ok(albumService.list());
    }
    /**
     * 根据Id查询
     * @Author: snowy
     * @Date: 2020/7/13
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        return Result.ok(albumService.getById(id));
    }

    /**
     * 新增
     * @Author: snowy
     * @Date: 2020/7/13
     * @Param: [album]
     * @return: com.snowy.tool.Result
     */
    @PostMapping
    public Result save(@RequestBody Album album){
        return Result.ok(albumService.save(album));
    }

    /**
     * 修改
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: [album]
     * @return: com.snowy.tool.Result
     */
    @PutMapping
    public Result updateById(@RequestBody Album album){
        return Result.ok(albumService.updateById(album));
    }

    /**
     * 删除
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return Result.ok(albumService.removeById(id));
    }

    



}
