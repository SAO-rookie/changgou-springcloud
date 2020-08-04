package com.snowy.changgou.content.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.changgou.content.entity.Content;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author snowy
 * @since 2020-08-02
 */
public interface ContentService extends IService<Content> {
    List<Content> getOneByCategoryId(Long id);
}
