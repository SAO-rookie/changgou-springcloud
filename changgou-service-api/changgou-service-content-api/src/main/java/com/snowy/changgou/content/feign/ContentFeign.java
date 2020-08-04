package com.snowy.changgou.content.feign;

import com.snowy.changgou.content.entity.Content;
import com.snowy.changgou.content.tool.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author snowy
 * @date 2020/8/3 21:34
 */
@FeignClient(name="changgou-content")
@RequestMapping(value ="/content")
public interface ContentFeign {

    /***
     * 根据分类ID查询所有广告
     */
    @GetMapping(value = "/list/category/{id}")
    List<Content> findByCategory(@PathVariable Long id);
}
