package com.snowy.changgou.order.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 雪域人
 * @date 2020-08-27 17:26:14
 * @email 
 */
@Data
@ToString
@TableName("tb_return_order_item")
public class ReturnOrderItem implements Serializable {

    // ID
    @TableId
    private Long id;

    // 分类ID
    private Long categoryId;

    // SPU_ID
    private Long spuId;

    // SKU_ID
    private Long skuId;

    // 订单ID
    private Long orderId;

    // 订单明细ID
    private Long orderItemId;

    // 退货订单ID
    private Long returnOrderId;

    // 标题
    private String title;

    // 单价
    private Integer price;

    // 数量
    private Integer num;

    // 总金额
    private Integer money;

    // 支付金额
    private Integer payMoney;

    // 图片地址
    private String image;

    // 重量
    private Integer weight;

}