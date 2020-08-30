package com.snowy.changgou.pay.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author snowy
 * @date 2020/8/30 15:12
 */
@Configuration
public class MqConfig {
    @Value("${mq.pay.exchange.order}")
    private String exchange;
    @Value("${mq.pay.queue.order}")
    private String queue;
    @Value("${mq.pay.routing.key}")
    private String routing;

    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(exchange,true,false);
    }
    @Bean
    public Queue queueOrder(){
        return new Queue(queue,true);
    }
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(queueOrder()).to(basicExchange()).with(routing);
    }
}
