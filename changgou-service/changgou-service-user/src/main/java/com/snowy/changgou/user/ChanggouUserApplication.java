package com.snowy.changgou.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author snowy
 * @date 2020/8/21 21:40
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.snowy.changgou.user.mapper"})
public class ChanggouUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouUserApplication.class,args);
    }
}
