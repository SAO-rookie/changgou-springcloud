package com.snowy.changgou.goods.feign;

import com.snowy.changgou.goods.entity.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author snowy
 * @date 2020/8/27 17:59
 */
@FeignClient(name = "changgou-goods")
@RequestMapping("/spu")
public interface SpuFeign {
    @GetMapping("/{id}")
    Spu getSpuById(@PathVariable String id);
}
