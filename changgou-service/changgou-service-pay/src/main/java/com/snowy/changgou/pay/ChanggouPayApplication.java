package com.snowy.changgou.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author snowy
 * @date 2020/8/30 12:51
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class ChanggouPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouPayApplication.class,args);
    }
}
