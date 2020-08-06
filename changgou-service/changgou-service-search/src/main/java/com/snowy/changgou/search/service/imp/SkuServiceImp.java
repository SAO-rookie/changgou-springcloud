package com.snowy.changgou.search.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.goods.feign.SkuFeign;
import com.snowy.changgou.search.entity.SkuInfo;
import com.snowy.changgou.search.mapper.SkuEsMapper;
import com.snowy.changgou.search.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author snowy
 * @date 2020/8/5 21:35
 */
@Service
public class SkuServiceImp implements SkuService {
    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private SkuEsMapper skuEsMapper;
    @Override
    public void importSku() {

        List<Sku> skuList = skuFeign.findByStatus(1);
        List<SkuInfo> skuInfos=  JSON.parseArray(JSON.toJSONString(skuList),SkuInfo.class);
        skuInfos.stream().forEach(s->{
            Map<String, Object> specMap = JSONObject.parseObject(s.getSpec());
            s.setSpecMap(specMap);
        });
        skuEsMapper.saveAll(skuInfos);
    }
}
