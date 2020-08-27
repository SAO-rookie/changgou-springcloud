package com.snowy.changgou.order.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author 雪域人
 * @date 2020-08-27 17:26:14
 * @email 
 */
@Data
@ToString
@TableName("tb_order_config")
public class OrderConfig {

    // ID
    @TableId
    private Integer id;

    // 正常订单超时时间（分）
    private Integer orderTimeout;

    // 秒杀订单超时时间（分）
    private Integer seckillTimeout;

    // 自动收货（天）
    private Integer takeTimeout;

    // 售后期限
    private Integer serviceTimeout;

    // 自动五星好评
    private Integer commentTimeout;

}