package com.snowy.changgou.order.controller;

import com.snowy.changgou.content.tool.Result;
import com.snowy.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author snowy
 * @date 2020/8/27 17:52
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;

    /*
     * @Description
     * @Author: snowy
     * @Date: 2020/8/27 19:11
     * @Param:[num, id]
     * @Return: com.snowy.changgou.content.tool.Result
     **/
    @GetMapping("/save")
    public Result saveCart(Integer num,String id){
        //将商品加入购物车
        cartService.save(num,id);
        return Result.ok("加入购物车成功！");
    }

    /*
     * @Description
     * @Author: snowy
     * @Date: 2020/8/27 19:12
     * @Param:[username]
     * @Return: com.snowy.changgou.content.tool.Result
     **/
    @GetMapping("/list")
    public Result list(){
        return Result.ok(cartService.list());
    }
}
