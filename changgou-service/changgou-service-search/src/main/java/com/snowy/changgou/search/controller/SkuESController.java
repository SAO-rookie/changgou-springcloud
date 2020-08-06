package com.snowy.changgou.search.controller;

import com.snowy.changgou.content.tool.Result;
import com.snowy.changgou.content.tool.StatusCode;
import com.snowy.changgou.search.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author snowy
 * @date 2020/8/5 21:48
 */
@RestController
@RequestMapping("/ES")
public class SkuESController {
    @Autowired
    private SkuService skuService;

    @GetMapping("/import")
    public Result search(){
        skuService.importSku();
        return  Result.ok("导入数据到索引库中成功！");
    }


}
