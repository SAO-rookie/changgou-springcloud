package com.snowy.changgou.order.entity;


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
@TableName("tb_category_report")
public class CategoryReport {

    // 1级分类
    private Integer categoryId1;

    // 2级分类
    private Integer categoryId2;

    // 3级分类
    private Integer categoryId3;

    // 统计日期
    private Date countDate;

    // 销售数量
    private Integer num;

    // 销售额
    private Integer money;

}