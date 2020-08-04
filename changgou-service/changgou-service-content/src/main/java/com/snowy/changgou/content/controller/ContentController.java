package com.snowy.changgou.content.controller;


import com.snowy.changgou.content.entity.Content;
import com.snowy.changgou.content.service.ContentService;
import com.snowy.changgou.content.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author snowy
 * @since 2020-08-02
 */
@RestController
@RequestMapping("/content")
@CrossOrigin
public class ContentController {
    @Autowired
    private ContentService contentService;
    /*
     * @Description 根据分类id查询
     * @Author: snowy
     * @Date: 2020/8/3 22:37
     * @Param:[id]
     * @Return: com.snowy.changgou.content.entity.Content
     **/
    @GetMapping(value ="/list/category/{id}")
    public List<Content> findByCategory(@PathVariable Long id){
        return  contentService.getOneByCategoryId(id);
    }

}

