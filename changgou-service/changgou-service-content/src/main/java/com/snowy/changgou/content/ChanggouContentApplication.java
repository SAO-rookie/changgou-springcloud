package com.snowy.changgou.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author snowy
 * @date 2020/8/2 23:26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ChanggouContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouContentApplication.class,args);
    }
}
