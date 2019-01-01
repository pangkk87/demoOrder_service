package cn.blackboss.order_service.service.impl;

import cn.blackboss.order_service.domain.ProductOrder;
import cn.blackboss.order_service.service.ProductClient;
import cn.blackboss.order_service.service.ProductOrderService;
import cn.blackboss.order_service.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private ProductClient productClient;


    @Override
    public ProductOrder save(int userId, int productId) {

//        Object obj = restTemplate.getForObject("http://product-service/api/v1/product/find?id="+productId,Object.class);
//        System.out.println(obj);
        String response =  productClient.findById(productId);

        JsonNode jsonNode = JsonUtils.str2JsonNode(response);


        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
