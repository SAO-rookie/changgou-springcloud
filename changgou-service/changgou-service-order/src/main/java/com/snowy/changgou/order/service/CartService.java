package com.snowy.changgou.order.service;

import com.snowy.changgou.order.entity.OrderItem;

import java.util.List;

/**
 * @author snowy
 * @date 2020/8/27 17:53
 */
public interface CartService {
    void save(Integer num, String id);

    List<OrderItem> list();
}
