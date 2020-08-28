package com.snowy.changgou.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 雪域人
 * @date 2020-08-27 17:26:14
 * @email 
 */
@Data
@ToString
@TableName("tb_order_log")
public class OrderLog implements Serializable {

    // ID
    @TableId(type = IdType.INPUT)
    private String id;

    // 操作员
    private String operater;

    // 操作时间
    private Date operateTime;

    // 订单ID
    private String orderId;

    // 订单状态,0未完成，1已完成，2，已退货
    private String orderStatus;

    // 付款状态  0:未支付，1：已支付，2：支付失败
    private String payStatus;

    // 发货状态
    private String consignStatus;

    // 备注
    private String remarks;

    // 支付金额
    private Integer money;

    private String username;

}