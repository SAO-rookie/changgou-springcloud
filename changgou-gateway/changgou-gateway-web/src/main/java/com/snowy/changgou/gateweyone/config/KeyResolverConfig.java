package com.snowy.changgou.gateweyone.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author snowy
 * @date 2020/8/20 20:25
 */
@Slf4j
@Configuration
public class KeyResolverConfig {
    /***
     * IP限流
     * @return
     */
    @Bean(name="ipKeyResolver")
    public KeyResolver userKeyResolver(){
        return e-> Mono.just(e.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }


}
