package com.snowy.changgou.order.service.imp;

import cn.hutool.core.util.StrUtil;
import com.snowy.changgou.content.tool.IdWorker;
import com.snowy.changgou.content.tool.TokenDecode;
import com.snowy.changgou.goods.entity.Sku;
import com.snowy.changgou.goods.entity.Spu;
import com.snowy.changgou.goods.feign.SkuFeign;
import com.snowy.changgou.goods.feign.SpuFeign;
import com.snowy.changgou.order.entity.OrderItem;
import com.snowy.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author snowy
 * @date 2020/8/27 18:06
 */
@Service
public class CartServiceImp implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SpuFeign spuFeign;
    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private TokenDecode tokenDecode;



    @Override
    public void save(Integer num, String id) {
        String username = tokenDecode.getUserInfo().get("username");
        if (num <=0) {
            redisTemplate.boundHashOps("Cart_"+username).delete();
            return;
        }
        Sku sku = skuFeign.findById(id);
        Optional.ofNullable(sku).ifPresent(s->{
            OrderItem item = sku2OrderItem(sku, num);
            redisTemplate.boundHashOps("Cart_"+username).put(id,item);
        });
    }

    @Override
    public List<OrderItem> list() {
        String username = tokenDecode.getUserInfo().get("username");
        List<OrderItem> orderItems = redisTemplate.boundHashOps("Cart_" + username).values();
        return orderItems;
    }

    private OrderItem sku2OrderItem(Sku sku,Integer num){
        Spu spu = spuFeign.getSpuById(sku.getSpuId());
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(sku.getId());
        orderItem.setSpuId(spu.getId());
        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(num);
        orderItem.setMoney(num*orderItem.getPrice());
        orderItem.setPayMoney(num*orderItem.getPrice());    //实付金额
        orderItem.setImage(sku.getImage());
        orderItem.setWeight(sku.getWeight()*num);           //重量=单个重量*数量
        //分类ID设置
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        return orderItem;
    }
}
