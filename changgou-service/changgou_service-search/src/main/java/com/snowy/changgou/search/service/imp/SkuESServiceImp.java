package com.snowy.changgou.search.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.goods.feign.SkuFeign;
import com.snowy.changgou.search.entity.SkuInfo;
import com.snowy.changgou.search.mapper.SkuEsMapper;
import com.snowy.changgou.search.service.SkuESService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author snowy
 * @date 2020/8/7 22:08
 */
@Service
public class SkuESServiceImp implements SkuESService {
    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private SkuEsMapper skuEsMapper;
    @Override
    public void importSku() {
        //调用changgou-service-goods微服务
        List<Sku> skuListResult = skuFeign.findByStatus(1);
        //将数据转成search.Sku
        List<SkuInfo> skuInfos=  JSONObject.parseArray(JSONObject.toJSONString(skuListResult),SkuInfo.class);
        for(SkuInfo skuInfo:skuInfos){
            Map<String, Object> specMap= JSONObject.parseObject(skuInfo.getSpec()) ;
            skuInfo.setSpecMap(specMap);
        }
        skuEsMapper.saveAll(skuInfos);
    }

    @Override
    public Map search(Map<String, String> searchMap) {
        String  keywords = Optional.ofNullable(searchMap.get("keywords")).orElse("华为");
        //2.创建查询对象 的构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //3.设置查询的条件
        // 所有字段匹配
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", keywords));
        //设置分组条件  商品分类
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders
                .terms("skuCategorygroup")
                .field("categoryName").size(50)
                );
        // 品牌 条件
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders
                .terms("skuBrandgroup")
                .field("brandName").size(50)
        );
        //设置分组条件  商品的规格
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders
                .terms("skuSpecgroup")
                .field("spec.keyword")
                .size(100));
        //4.构建查询对象
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        //5.执行查询
        AggregatedPage<SkuInfo> skuPage = esTemplate.queryForPage(query, SkuInfo.class);
        //6.返回结果
        //获取分组结果  商品分类
        StringTerms stringTermsCategory = (StringTerms)skuPage.getAggregation("skuCategorygroup");
        //获取分组结果  商品品牌
        StringTerms stringTermsBrand = (StringTerms) skuPage.getAggregation("skuBrandgroup");
        //获取分组结果  商品品牌
        StringTerms stringTermsSpec = (StringTerms) skuPage.getAggregation("skuSpecgroup");

        Map resultMap = new HashMap<>();
        resultMap.put("rows", skuPage.getContent());
        resultMap.put("total", skuPage.getTotalElements());
        resultMap.put("totalPages", skuPage.getTotalPages());
        resultMap.put("categoryList", dataProcessing(stringTermsCategory));
        resultMap.put("brandList", dataProcessing(stringTermsBrand));
        resultMap.put("specMap", getStringSetMap(stringTermsSpec));
        return resultMap;
    }

    // 商品分类数据处理
    private List<String>  dataProcessing(StringTerms stringTerms){
      return  Optional.ofNullable(stringTerms)
              .map(s -> s.getBuckets()
                      .stream()
                      .map(StringTerms.Bucket::getKeyAsString)
                      .collect(Collectors.toList()))
              .orElse(Collections.emptyList());
    }

    private Map<String, Set<String>> getStringSetMap(StringTerms stringTerms){
        Map<String, Set<String>> specMap = new HashMap<String, Set<String>>();
        Set<String> specList = Optional.ofNullable(stringTerms)
                .map(s -> s.getBuckets()
                        .stream()
                        .map(StringTerms.Bucket::getKeyAsString)
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());

        for (String specjson : specList) {
            Map<String, String> map = JSON.parseObject(specjson, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {//
                String key = entry.getKey();        //规格名字
                String value = entry.getValue();    //规格选项值
                //获取当前规格名字对应的规格数据
                Set<String> specValues = Optional.ofNullable(specMap.get(key))
                        .orElse(new HashSet<String>())
                        .stream()
                        .collect(Collectors.toSet());
                //将当前规格加入到集合中
                specValues.add(value);
                //将数据存入到specMap中
                specMap.put(key, specValues);
            }
        }
        return specMap;
    }


}
