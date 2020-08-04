package com.snowy.changgou.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author snowy
 * @date 2020/8/2 23:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@MapperScan(basePackages = {"com.snowy.changgou.content.mapper"})
public class ChanggouContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouContentApplication.class,args);
    }
}
