package com.snowy.changgou.goods.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.snowy.changgou.content.tool.FeignInterceptor;
import com.snowy.changgou.content.tool.IdWorker;
import com.snowy.changgou.content.tool.TokenDecode;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @auther snowy
 * @date 2020/7/10 - 22:11
 */
//Spring boot方式
@EnableTransactionManagement //开启事务
@Configuration
@MapperScan("com.snowy.changgou.goods.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
    /***
     * 创建拦截器Bean对象
     * @return
     */
    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }
}
