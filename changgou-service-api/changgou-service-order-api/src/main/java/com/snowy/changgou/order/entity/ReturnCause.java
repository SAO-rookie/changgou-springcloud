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
@TableName("tb_return_cause")
public class ReturnCause {

    // ID
    @TableId
    private Integer id;

    // 原因
    private String cause;

    // 排序
    private Integer seq;

    // 是否启用
    private String status;

}