package com.snowy.changgou.search.mapper;

import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.search.entity.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author snowy
 * @date 2020/8/7 22:23
 */
@Repository
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,String> {
}
