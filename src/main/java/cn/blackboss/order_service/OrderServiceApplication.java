package cn.blackboss.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 订单服务
 * 引入 web -- web
 *     cloud discovery  -- euraka discovery
 *     cloud routing --  ribbon
 *
 */
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}


	/**
	 * 包装设计模式
	 * 包装了 httpClient
	 *
	 * LoadBalanced标识是一个负载均衡器
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}

