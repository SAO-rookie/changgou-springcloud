package com.snowy.test;

import com.snowy.changgou.content.tool.TokenDecode;
import com.snowy.changgou.order.ChanggouOrderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author snowy
 * @date 2020/8/28 15:57
 */
@SpringBootTest(classes = ChanggouOrderApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void dsa(){
        Object o = redisTemplate.boundHashOps("Cart_zhangsan").get("100000030900");
        System.out.println(o);
    }
}
