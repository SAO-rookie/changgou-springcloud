package com.snowy.changgou.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author snowy
 * @date 2020/8/27 17:51
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.snowy.changgou.goods.feign"})
@MapperScan(basePackages = {"com.snowy.changgou.order.mapper"})
@EnableCaching
public class ChanggouOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouOrderApplication.class,args);
    }
}
