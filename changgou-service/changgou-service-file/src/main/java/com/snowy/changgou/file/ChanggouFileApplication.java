package com.snowy.changgou.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther snowy
 * @date 2020/7/11 - 11:34
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class ChanggouFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouFileApplication.class,args);
    }
}
