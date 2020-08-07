package com.snowy.changgou.search.controller;

import com.snowy.changgou.content.tool.Result;
import com.snowy.changgou.content.tool.StatusCode;
import com.snowy.changgou.search.service.SkuESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author snowy
 * @date 2020/8/7 22:11
 */
@RestController
@RequestMapping("/ES")
@CrossOrigin
public class SkuESController {

    @Autowired
    private SkuESService skuESService;

    @GetMapping("/import")
    public Result search(){
        skuESService.importSku();
        return  Result.ok("导入数据到索引库中成功！");
    }

    @GetMapping
    public Map search(@RequestBody(required = false) Map searchMap){
        return  skuESService.search(searchMap);
    }
}
