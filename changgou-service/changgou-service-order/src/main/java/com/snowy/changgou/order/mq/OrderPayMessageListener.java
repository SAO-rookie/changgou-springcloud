package com.snowy.changgou.order.mq;

import com.alibaba.fastjson.JSONObject;
import com.snowy.changgou.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author snowy
 * @date 2020/8/30 15:52
 */
@Component
@RabbitListener(queues = {"${mq.pay.queue.order}"})
public class OrderPayMessageListener {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void consumeMessage(String msg){
        Map<String,String> result = JSONObject.parseObject(msg, Map.class);
        //return_code=SUCCESS
        Optional.ofNullable(result.get("return_code"))
                .filter(s -> "success".equalsIgnoreCase(s))
                .ifPresent(a->{
                    //业务结果
                    if("success".equalsIgnoreCase(result.get("result_code"))){
                        //修改订单状态  out_trade_no
                        Optional.ofNullable(result.get("out_trade_no")).ifPresent(o->orderService.updateOrderStatus(o,result.get("transaction_id")));
                    }else{
                        //订单删除
                        orderService.deleteOrder(result.get("out_trade_no"));
                    }
                });
    }
}
