package com.snowy.changgou.canal;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableCanalClient
@EnableDiscoveryClient
public class ChanggouCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouCanalApplication.class,args);
    }
}
