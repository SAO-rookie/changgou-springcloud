package com.snowy.changgou.pay.config;

import com.snowy.changgou.content.tool.TokenDecode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author snowy
 * @date 2020/8/21 23:32
 */
@Configuration
public class WebConfg {
    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}
