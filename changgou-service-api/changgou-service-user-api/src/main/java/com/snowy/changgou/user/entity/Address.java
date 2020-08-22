package com.snowy.changgou.user.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 雪域人
 * @date 2020-08-21 21:25:30
 * @email 
 */
@Data
@ToString
public class Address implements Serializable {

    private Integer id;

    // 用户名
    private String username;

    // 省
    private String provinceid;

    // 市
    private String cityid;

    // 县/区
    private String areaid;

    // 电话
    private String phone;

    // 详细地址
    private String address;

    // 联系人
    private String contact;

    // 是否是默认 1默认 0否
    private String isDefault;

    // 别名
    private String alias;

}