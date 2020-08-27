package com.snowy.changgou.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 雪域人
 * @date 2020-08-27 17:26:14
 * @email 
 */
@Data
@ToString
@TableName("tb_preferential")
public class Preferential {

    // ID
    @TableId
    private Integer id;

    // 消费金额
    private Integer buyMoney;

    // 优惠金额
    private Integer preMoney;

    // 品类ID
    private Long categoryId;

    // 活动开始日期
    private Date startTime;

    // 活动截至日期
    private Date endTime;

    // 状态
    private String state;

    // 类型1不翻倍 2翻倍
    private String type;

}