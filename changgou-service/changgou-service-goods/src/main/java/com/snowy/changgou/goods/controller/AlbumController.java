package com.snowy.changgou.goods.controller;

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
     * 分页
     * @Author: snowy
     * @Date: 2020/7/13
     * @Param: [page]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/page")
    public Result getAll(Page< Album > page){
        return Result.ok(albumService.page(page));
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

    



}
