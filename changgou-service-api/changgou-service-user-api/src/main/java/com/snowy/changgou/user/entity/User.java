package com.snowy.changgou.user.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author snowy
 * @date 2020/8/21 21:20
 */
@Data
@ToString
public class User {

    // 用户名
    private String username;

    // 密码，加密存储
    private String password;

    // 注册手机号
    private String phone;

    // 注册邮箱
    private String email;

    // 创建时间
    private Date created;

    // 修改时间
    private Date updated;

    // 会员来源：1:PC，2：H5，3：Android，4：IOS
    private String sourceType;

    // 昵称
    private String nickName;

    // 真实姓名
    private String name;

    // 使用状态（1正常 0非正常）
    private String status;

    // 头像地址
    private String headPic;

    // QQ号码
    private String qq;

    // 手机是否验证 （0否  1是）
    private String isMobileCheck;

    // 邮箱是否检测（0否  1是）
    private String isEmailCheck;

    // 性别，1男，0女
    private String sex;

    // 会员等级
    private Integer userLevel;

    // 积分
    private Integer points;

    // 经验值
    private Integer experienceValue;

    // 出生年月日
    private Date birthday;

    // 最后登录时间
    private Date lastLoginTime;

}