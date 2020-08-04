package com.snowy.changgou.content.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.content.entity.Content;
import com.snowy.changgou.content.mapper.ContentMapper;
import com.snowy.changgou.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snowy
 * @since 2020-08-02
 */
@Service
@CacheConfig(cacheNames ="content")
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {
    @Autowired
    private ContentMapper contentMapper;
    @Override
    @Cacheable(key = "#id")
    public List<Content> getOneByCategoryId(Long id) {
        return contentMapper.selectList(Wrappers.<Content>lambdaQuery()
                .eq(Content::getCategoryId,id)
                .eq(Content::getStatus,1));
    }
}
