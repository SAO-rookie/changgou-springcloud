package com.snowy.changgou.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.snowy.changgou.content.tool.Result;
import com.snowy.changgou.pay.service.WeixinPayService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author snowy
 * @date 2020/8/30 13:22
 */
@RestController
@RequestMapping("/weiPay")
public class WeixinPayController {
    @Value("${mq.pay.exchange.order}")
    private String exchange;
    @Value("${mq.pay.queue.order}")
    private String queue;
    @Value("${mq.pay.routing.key}")
    private String routing;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private WeixinPayService weixinPayService;

    @GetMapping("/createNative")
    public Result createNative(String outTradeNo, String totalFee){
        return Result.ok(weixinPayService.createNative(outTradeNo,totalFee));
    }

    @GetMapping("/checkStatus")
    public Result checkStatus(String outTradeNo){
        return Result.ok(weixinPayService.queryPayStatus(outTradeNo));
    }

    @RequestMapping(value = "/notifyUrl")
    public String notifyUrl(HttpServletRequest request){
        InputStream inStream = null;
        try {
            inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1){
                outSteam.write(buffer,0,len);
            }
            outSteam.close();
            inStream.close();
            // 将支付回调数据转换成xml字符串
            String result = new String(outSteam.toByteArray(), "utf-8");
            //将xml字符串转换成Map结构
            Map<String, String> map = WXPayUtil.xmlToMap(result);
            rabbitTemplate.convertAndSend(exchange,routing, JSONObject.toJSONString(map));

            //响应数据设置
            Map respMap = new HashMap();
            respMap.put("return_code","SUCCESS");
            respMap.put("return_msg","OK");
            return WXPayUtil.mapToXml(respMap);
        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
    }
}
