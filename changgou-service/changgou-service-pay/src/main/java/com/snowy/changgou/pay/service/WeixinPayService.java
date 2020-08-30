package com.snowy.changgou.pay.service;

import java.util.Map;

/**
 * @author snowy
 * @date 2020/8/30 12:54
 */
public interface WeixinPayService {
     Map createNative(String outTradeNo, String totalFee);
     Map queryPayStatus(String outTradeNo);
}
