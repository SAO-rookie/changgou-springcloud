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
public class Cities implements Serializable {

    // 城市ID
    private String cityid;

    // 城市名称
    private String city;

    // 省份ID
    private String provinceid;

}