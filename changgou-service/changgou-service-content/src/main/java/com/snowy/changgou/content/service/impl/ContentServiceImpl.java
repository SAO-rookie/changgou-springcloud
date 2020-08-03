package com.snowy.changgou.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.changgou.entity.Content;
import com.snowy.changgou.content.mapper.ContentMapper;
import com.snowy.changgou.content.service.ContentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snowy
 * @since 2020-08-02
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

}
