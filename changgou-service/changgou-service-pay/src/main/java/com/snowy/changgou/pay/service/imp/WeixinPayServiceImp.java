package com.snowy.changgou.pay.service.imp;

import com.github.wxpay.sdk.WXPayUtil;
import com.snowy.changgou.content.tool.HttpClient;
import com.snowy.changgou.pay.service.WeixinPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author snowy
 * @date 2020/8/30 12:54
 */
@Service
public class WeixinPayServiceImp implements WeixinPayService {
    @Value("${weixin.appid}")
    private String appId;
    @Value("${weixin.partner}")
    private String partner;
    @Value("${weixin.partnerkey}")
    private String partnerkey;
    @Value("${weixin.notifyurl}")
    private String notifyUrl;

    private static final String  UNIFIED_ORDER_ADDREES = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String  CHECKING_ORDER = "https://api.mch.weixin.qq.com/pay/orderquery";

    @Override
    public Map createNative(String outTradeNo, String totalFee) {
        return sendPaymentRequest(outTradeNo, totalFee);
    }

    @Override
    public Map queryPayStatus(String outTradeNo) {
        return checkPaymentStatus(outTradeNo);
    }


    /*------封装方法--------*/
    /*
     * @Description:下单处理
     * @Author: snowy
     * @Date: 2020/8/30 14:15
     * @Param:[outTradeNo, totalFee]
     * @Return: java.util.Map<java.lang.String,java.lang.String>
     **/
    private Map<String, String> sendPaymentRequest(String outTradeNo, String totalFee){
        try {
            //1、封装参数
            Map param = new HashMap();
            param.put("appid", appId);                              //应用ID
            param.put("mch_id", partner);                           //商户ID号
            param.put("nonce_str", WXPayUtil.generateNonceStr());   //随机数
            param.put("body", "畅购");                            	//订单描述
            param.put("out_trade_no",outTradeNo);                 //商户订单号
            param.put("total_fee", totalFee);                      //交易金额
            param.put("spbill_create_ip", "127.0.0.1");           //终端IP
            param.put("notify_url", notifyUrl);                    //回调地址
            param.put("trade_type", "NATIVE");                     //交易类型
            //发送请求
            String signedXml = WXPayUtil.generateSignedXml(param, partnerkey);
            HttpClient httpClient = new HttpClient(UNIFIED_ORDER_ADDREES);
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();
            // 处理返回参数
            Map<String,String> dataMap = new HashMap<String,String>();
            Map<String, String> stringStringMap = WXPayUtil.xmlToMap(httpClient.getContent());
            dataMap.put("code_url",stringStringMap.get("code_url"));
            dataMap.put("out_trade_no",outTradeNo);
            dataMap.put("total_fee",totalFee);
            return dataMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }
    /*
     * @Description: 查询订单支付状态
     * @Author: snowy
     * @Date: 2020/8/30 14:19
     * @Param:[outTradeNo]
     * @Return: java.util.Map
     **/
    public Map checkPaymentStatus(String outTradeNo){
        try {
            //1.封装参数
            Map param = new HashMap();
            param.put("appid",appId);                            //应用ID
            param.put("mch_id",partner);                         //商户号
            param.put("out_trade_no",outTradeNo);              //商户订单编号
            param.put("nonce_str",WXPayUtil.generateNonceStr()); //随机字符

            //2、将参数转成xml字符，并携带签名
            String paramXml = WXPayUtil.generateSignedXml(param,partnerkey);

            //3、发送请求
            HttpClient httpClient = new HttpClient(CHECKING_ORDER);
            httpClient.setHttps(true);
            httpClient.setXmlParam(paramXml);
            httpClient.post();
            //4、获取返回值，并将返回值转成Map
            return WXPayUtil.xmlToMap(httpClient.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
