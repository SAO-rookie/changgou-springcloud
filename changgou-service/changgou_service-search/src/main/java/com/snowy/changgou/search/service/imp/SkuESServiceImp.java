package com.snowy.changgou.search.service.imp;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.goods.feign.SkuFeign;
import com.snowy.changgou.search.entity.SkuInfo;
import com.snowy.changgou.search.mapper.SkuEsMapper;
import com.snowy.changgou.search.service.SkuESService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

        //2.创建查询对象 的构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //3.设置查询的条件
        // 所有字段匹配
        Optional.ofNullable(searchMap.get("keywords")).ifPresent(s->
                nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", s))
        );
        ;
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


        // 设置主关键字查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 品牌过滤
        Optional.ofNullable(searchMap.get("brand")).ifPresent(b-> boolQueryBuilder.filter(QueryBuilders.termQuery("brandName", b)));
        // 分类过滤
        Optional.ofNullable(searchMap.get("category")).ifPresent(c->boolQueryBuilder.filter(QueryBuilders.termQuery("categoryName",c)));
        // 规格参数过滤
        Optional.ofNullable(searchMap).ifPresent(s->{
            s.keySet()
                    .stream()
                    .filter(a->a.startsWith("spec_"))
                    .forEach(b-> boolQueryBuilder.filter(QueryBuilders.termQuery("specMap"+b.substring(5)+".keyword",searchMap.get(b))));
        });

        Optional.ofNullable(searchMap.get("price")).ifPresent(s -> {
            String[] split = s.split("-");
            if (!"*".equalsIgnoreCase(split[1])) {
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").from(split[0], true).to(split[1], true));
            } else {
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(split[0]));
            }
        });

        //构建过滤查询
        nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        //构建分页查询
        int pageNum = Integer.parseInt(Optional.ofNullable(searchMap.get("pageNum")).orElse("1"));
        Integer pageSize = 3;
        nativeSearchQueryBuilder.withPageable(PageRequest.of( pageNum - 1, pageSize));
        // 构建排序查询
        if (StrUtil.isNotEmpty(searchMap.get("sortRule"))&& StrUtil.isNotEmpty(searchMap.get("sortField"))){
            boolean sortRule = "DESC".equals(searchMap.get("sortRule"));
            nativeSearchQueryBuilder.withSort(SortBuilders
                    .fieldSort(searchMap.get("sortField"))
                    .order(sortRule ? SortOrder.DESC : SortOrder.ASC));
        }
        //4.构建查询对象
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        //5.执行查询
        AggregatedPage<SkuInfo> skuPage = esTemplate.queryForPage(query, SkuInfo.class);
        //6.返回结果
        Map resultMap = new HashMap<>();
        resultMap.put("rows", skuPage.getContent());
        resultMap.put("total", skuPage.getTotalElements());
        resultMap.put("totalPages", skuPage.getTotalPages());
        resultMap.put("categoryList", dataProcessing(skuPage,"skuCategorygroup"));
        resultMap.put("brandList", dataProcessing(skuPage,"skuBrandgroup"));
        resultMap.put("specMap", getStringSetMap(skuPage,"skuSpecgroup"));
        return resultMap;
    }


    // 商品分类数据处理
    private List<String>  dataProcessing(AggregatedPage<SkuInfo> skuInfos,String str){
        StringTerms stringTerms = (StringTerms)skuInfos.getAggregation(str);
        return  Optional.ofNullable(stringTerms)
                .map(s -> s.getBuckets()
                        .stream()
                        .map(StringTerms.Bucket::getKeyAsString)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private Map<String, Set<String>> getStringSetMap(AggregatedPage<SkuInfo> skuInfos,String str){
        StringTerms stringTerms = (StringTerms)skuInfos.getAggregation(str);
        Map<String, Set<String>> specMap = new HashMap<>();
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
