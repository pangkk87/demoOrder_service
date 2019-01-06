package cn.blackboss.order_service.failback;

import cn.blackboss.order_service.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * fegin结合Hystrix实现服务降级
 * 添加@Component被spring管理
 * 实现需要服务降级的接口服务 此处为ProductClient
 *
 */

@Component
public class ProductClientFailBack implements ProductClient {
    @Override
    public String findById(int id) {
        System.out.println("发短信通知运维，记录日志，开线程处理");
        return null;
    }
}
