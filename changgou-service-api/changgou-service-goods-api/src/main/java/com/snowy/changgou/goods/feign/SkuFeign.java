package com.snowy.changgou.goods.feign;

import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.order.entity.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/cutBack")
    boolean cutBack(@RequestParam("skuId") String skuId, @RequestParam("num")Integer num);
}
