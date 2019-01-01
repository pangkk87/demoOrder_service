package cn.blackboss.order_service.service.impl;

import cn.blackboss.order_service.domain.ProductOrder;
import cn.blackboss.order_service.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Override
    public ProductOrder save(int userId, int productId) {
/**
 *
 * LoadBalancer的choose方法来
 *   根据sid(service.id)获取服务列表
 *   通过策略来过滤出一个服务地址（ip+端口） 策略分为 轮询 权重 随机
 *   在通过restTemplate来调用服务   底层也是HTTPCLIENT.
 */
        ServiceInstance instance = loadBalancer.choose("product-service");
        String url = String.format("http://%s:%s/api/v1/product/find?id="+productId,instance.getHost(),instance.getPort());
        RestTemplate restTemplate = new RestTemplate();
        Map productMap = restTemplate.getForObject(url,Map.class);
//        System.out.println(obj);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(productMap.get("name").toString());
        productOrder.setPrice(Integer.parseInt(productMap.get("price").toString()));
        return productOrder;
    }
}
