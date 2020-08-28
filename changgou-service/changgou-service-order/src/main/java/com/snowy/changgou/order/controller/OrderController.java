package com.snowy.changgou.order.controller;

import com.snowy.changgou.content.tool.Result;
import com.snowy.changgou.order.entity.Order;
import com.snowy.changgou.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author snowy
 * @date 2020/8/28 13:17
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    /*
     * @Description
     * @Author: snowy
     * @Date: 2020/8/28 13:45
     * @Param:[order]
     * @Return: com.snowy.changgou.content.tool.Result
     **/
    @PostMapping
    public Result save(@RequestBody Order order){
        return Result.ok(orderService.saveOrder(order));
    }
}
