package com.snowy.changgou.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 内容分类
 * </p>
 *
 * @author snowy
 * @since 2020-08-02
 */
@TableName("tb_content_category")
@Data
public class ContentCategory implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 类目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String name;



}
