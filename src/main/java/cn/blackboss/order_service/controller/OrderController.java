package cn.blackboss.order_service.controller;

import cn.blackboss.order_service.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    ProductOrderService productOrderService;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderfail")//降级服务方法 和原方法参数一致
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id")int productId){
        Map<String,Object> msg = new HashMap<>();
        msg.put("code",0);
        msg.put("data",productOrderService.save(userId,productId));

        return msg;
    }

    //注意，降级方法签名一定要和api方法一致（参数及返回类型一致）
    private Object saveOrderfail(int userId, int productId){
        Map<String,Object> msg = new HashMap<>();
        msg.put("code",-1);
        msg.put("msg","访问失败，此信息为服务降级返回的数据");
        return msg;
    }
}
