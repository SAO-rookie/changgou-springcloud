package com.snowy.changgou.search.mapper;

import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.search.entity.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author snowy
 * @date 2020/8/5 21:33
 */
@Repository
public interface SkuEsMapper extends ElasticsearchCrudRepository<SkuInfo,String> {
}
