package cn.blackboss.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * EnableFeignClients 此注解
 * 是为了使用伪rpc框架 实质还是httpclient实现的 feign框架
 * 即可删除 RestTemplate 代码块（一直new，有点风险）
 *
 * 1 在pom中引入 feign的依赖
 * 2 在启动类添加feign注解
 * 3 添加接口并添加注解@FeignClient(name = "product-service")
 *
 */
@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}


//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}
}

