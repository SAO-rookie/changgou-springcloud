package com.snowy.changgou.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.changgou.goods.service.TemplateService;
import com.snowy.changgou.goods.entity.Template;
import com.snowy.changgou.content.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@RestController
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

    /**
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: [template]
     * @return: com.snowy.tool.Result
     */
    @PostMapping
    public Result save(@RequestBody Template template){
        return Result.ok(templateService.save(template));
    }

    /**
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: [template]
     * @return: com.snowy.tool.Result
     */
    @PutMapping
    public Result updateById(@RequestBody Template template){
        return Result.ok(templateService.updateById(template));
    }


    /**
     * @Author: snowy
     * @Date: 2020/7/14
     * @Param: [id]
     * @return: com.snowy.tool.Result
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        return Result.ok(templateService.removeById(id));
    }

    @GetMapping("/category/{id}")
    public Result getTemplateByCategoryId(@PathVariable Integer id){
        return Result.ok(templateService.findByCategoryId(id));
    }
}

