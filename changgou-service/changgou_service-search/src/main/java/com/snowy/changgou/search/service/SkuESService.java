package com.snowy.changgou.search.service;

import java.util.Map;

/**
 * @author snowy
 * @date 2020/8/7 22:07
 */
public interface SkuESService {
    void importSku();

    Map search(Map<String, String> searchMap);
}
