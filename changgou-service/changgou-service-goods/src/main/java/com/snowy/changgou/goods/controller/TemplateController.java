package com.snowy.changgou.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.changgou.goods.entity.Template;
import com.snowy.changgou.goods.service.TemplateService;
import com.snowy.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.sql.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@Controller
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    /**
     * 分页查询带条件
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: [page, template]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/page")
    public Result getAllPage(Page page,Template template){
        return Result.ok(templateService.page(page, Wrappers.query(template)));
    }

    /**
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: [template]
     * @return: com.snowy.tool.Result
     */
    @GetMapping("/getAllCondition")
    public Result getAllCondition(Template template){
        return Result.ok(templateService.list( Wrappers.query(template)));
    }

}

