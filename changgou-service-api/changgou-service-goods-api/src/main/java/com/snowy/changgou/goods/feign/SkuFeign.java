package com.snowy.changgou.goods.feign;

import com.snowy.changgou.goods.entity.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author snowy
 * @date 2020/8/5 21:30
 */
@FeignClient(name = "changgou-goods")
@RequestMapping("/sku")
public interface SkuFeign {
    @GetMapping("/status/{status}")
    List<Sku> findByStatus(@PathVariable int status);

    @GetMapping("/{id}")
     Sku findById(@PathVariable String id);
}
