package com.snowy.changgou.order.config;

import com.snowy.changgou.content.tool.FeignInterceptor;
import com.snowy.changgou.content.tool.IdWorker;
import com.snowy.changgou.content.tool.TokenDecode;
import org.junit.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author snowy
 * @date 2020/8/27 18:17
 */
@Configuration
public class WebConig {
    @Bean
    public IdWorker IdWorker(){
        return new IdWorker();
    }
    /***
     * 创建拦截器Bean对象
     * @return
     */
    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }

    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}
