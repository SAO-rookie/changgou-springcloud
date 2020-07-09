package com.snowy.changgou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther snowy
 * @date 2020/7/9 - 20:57
 */
@SpringBootApplication
@MapperScan(value = "com.snowy.changgou.mapper")
public class ChanggouGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChanggouGoodsApplication.class,args);
    }
}
