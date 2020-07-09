package com.snowy.changgou.controller;

import com.snowy.changgou.service.BrandService;
import com.snowy.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther snowy
 * @date 2020/7/9 - 22:24
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

   @GetMapping
    public Result findAll(){
        return Result.ok(brandService.list());
    }

}
