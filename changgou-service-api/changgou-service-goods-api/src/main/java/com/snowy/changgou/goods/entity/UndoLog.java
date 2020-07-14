package com.snowy.changgou.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author DmZ
 * @since 2020-07-14
 */
@TableName("undo_log")
@Data
public class UndoLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long branchId;

    private String xid;

    private Blob rollbackInfo;

    private Integer logStatus;

    private LocalDateTime logCreated;

    private LocalDateTime logModified;

    private String ext;
}
