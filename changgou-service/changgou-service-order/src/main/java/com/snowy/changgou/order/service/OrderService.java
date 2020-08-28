package com.snowy.changgou.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.changgou.order.entity.Order;

/**
 * @author snowy
 * @date 2020/8/28 13:19
 */
public interface OrderService extends IService<Order> {
    boolean saveOrder(Order order);
}
