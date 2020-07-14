package com.snowy.changgou.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@TableName("tb_pref")
@Data
public class Pref implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类ID
     */
    private Integer cateId;

    /**
     * 消费金额
     */
    private Integer buyMoney;

    /**
     * 优惠金额
     */
    private Integer preMoney;

    /**
     * 活动开始日期
     */
    private LocalDate startTime;

    /**
     * 活动截至日期
     */
    private LocalDate endTime;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private String state;


}
