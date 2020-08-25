package com.snowy.changgou.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author snowy
 * @date 2020/8/23 18:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.snowy.changgou.user.feign"})
public class ChanggouOAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouOAuthApplication.class,args);
    }
}
