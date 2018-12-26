package cn.blackboss.order_service.service;

import cn.blackboss.order_service.domain.ProductOrder;

public interface ProductOrderService {

    ProductOrder save(int userId,int productId);
}
