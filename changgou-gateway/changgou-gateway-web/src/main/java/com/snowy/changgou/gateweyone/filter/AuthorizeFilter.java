package com.snowy.changgou.gateweyone.filter;

import cn.hutool.core.util.StrUtil;
import com.snowy.changgou.gateweyone.tool.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author snowy
 * @date 2020/8/22 22:38
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取Request、Response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 获取请求的URI
        String path = request.getURI().getPath();
        // 如果是登录、goods等开放的微服务[这里的goods部分开放],则直接放行,这里不做完整演示，完整演示需要设计一套权限系统
        if (path.startsWith("/api/user")){
            return chain.filter(exchange);
        }
        // 获取头文件中的令牌信息
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        //如果头文件中没有，则从请求参数中获取
        if (StrUtil.isEmpty(token)){
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        }
        //如果cookie获取
        HttpCookie first = request.getCookies().getFirst(AUTHORIZE_TOKEN);
        if (Optional.ofNullable(first).isPresent()){
            token = first.getValue();
        }

        //如果为空，则输出错误代码
        if (StrUtil.isEmpty(token)){
            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
            return response.setComplete();
        }
        //解析令牌数据
        try {
            JwtUtil.decryptToken(token);
            request.mutate().header(AUTHORIZE_TOKEN,token);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //放行
        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
