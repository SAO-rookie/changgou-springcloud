package com.snowy.changgou.order.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.content.tool.IdWorker;
import com.snowy.changgou.content.tool.TokenDecode;
import com.snowy.changgou.goods.feign.SkuFeign;
import com.snowy.changgou.order.entity.Order;
import com.snowy.changgou.order.entity.OrderItem;
import com.snowy.changgou.order.mapper.OrderItemMapper;
import com.snowy.changgou.order.mapper.OrderMapper;
import com.snowy.changgou.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author snowy
 * @date 2020/8/28 13:20
 */
@Service
public class OrderServiceImp extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private TokenDecode tokenDecode;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SkuFeign skuFeign;
    @Override
    public boolean saveOrder(Order order) {
        String username = tokenDecode.getUserInfo().get("username");
        //查询出用户的所有购物车
        /*List<String> skuIds1 = order.getSkuIds();
        List<OrderItem> orderItems = new ArrayList<>();
        for (String s : skuIds1){
            OrderItem orderItem = (OrderItem)redisTemplate.boundHashOps("Cart_" + username).get(s);
            orderItems.add(orderItem);
        }*/
        List<OrderItem> orderItems = order.getSkuIds().stream().map(q -> (OrderItem) redisTemplate.boundHashOps("Cart_" + username).get(q)).collect(Collectors.toList());
        //统计计算
        int totalMoney = orderItems.stream().mapToInt(OrderItem::getMoney).sum(); //总金额
        int totalPayMoney = orderItems.stream().mapToInt(OrderItem::getPayMoney).sum(); //实际支付金额
        int num = orderItems.stream().mapToInt(OrderItem::getNum).sum(); //总数量
        order.setTotalNum(num);
        order.setTotalMoney(totalMoney);
        order.setPayMoney(totalPayMoney);
        //其他数据完善
        order.setId(String.valueOf(idWorker.nextId()));
        order.setUsername(username);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setBuyerRate("0");        //0:未评价，1：已评价
        order.setSourceType("1");       //来源，1：WEB
        order.setOrderStatus("0");      //0:未完成,1:已完成，2：已退货
        order.setPayStatus("0");        //0:未支付，1：已支付，2：支付失败
        order.setConsignStatus("0");    //0:未发货，1：已发货，2：已收货

        // 保存订单数据
        int insert = orderMapper.insert(order);
        orderItems.stream().forEach(o->{
            o.setId(String.valueOf(idWorker.nextId()));
            o.setIsReturn("0");
            o.setOrderId(order.getId());
            orderItemMapper.insert(o);
            skuFeign.cutBack(o.getSkuId(),o.getNum());
        });
      //清除Redis缓存购物车数据
        List<String> skuIds = order.getSkuIds();
        Optional.ofNullable(skuIds).ifPresent(c->c.stream().forEach(s->redisTemplate.boundHashOps("Cart_"+order.getUsername()).delete(s)));
        return insert>0;
    }
}
