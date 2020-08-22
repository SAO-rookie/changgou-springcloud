package com.snowy.changgou.user.entity;


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
public class Areas implements Serializable {

    // 区域ID
    private String areaid;

    // 区域名称
    private String area;

    // 城市ID
    private String cityid;

}