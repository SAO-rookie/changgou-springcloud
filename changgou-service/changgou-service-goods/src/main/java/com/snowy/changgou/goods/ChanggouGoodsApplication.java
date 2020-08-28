package com.snowy.changgou.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther snowy
 * @date 2020/7/9 - 20:57
 */
@SpringBootApplication
@MapperScan(value = "com.snowy.changgou.goods.mapper")
@EnableDiscoveryClient
@EnableCaching
public class ChanggouGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouGoodsApplication.class,args);
    }
}
