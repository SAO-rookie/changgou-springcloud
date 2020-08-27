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
@TableName("tb_order_item")
public class ReturnOrder {

    // 服务单号
    @TableId
    private Long id;

    // 订单号
    private Long orderId;

    // 申请时间
    private Date applyTime;

    // 用户ID
    private Long userId;

    // 用户账号
    private String userAccount;

    // 联系人
    private String linkman;

    // 联系人手机
    private String linkmanMobile;

    // 类型
    private String type;

    // 退款金额
    private Integer returnMoney;

    // 是否退运费
    private String isReturnFreight;

    // 申请状态
    private String status;

    // 处理时间
    private Date disposeTime;

    // 退货退款原因
    private Integer returnCause;

    // 凭证图片
    private String evidence;

    // 问题描述
    private String description;

    // 处理备注
    private String remark;

    // 管理员id
    private Integer adminId;

}