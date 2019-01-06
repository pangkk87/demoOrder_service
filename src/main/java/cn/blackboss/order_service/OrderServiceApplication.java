package cn.blackboss.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 增加feign模块，提供服务之间的交互
 *
 * EnableFeignClients 此注解
 * 是为了使用伪rpc框架 实质还是httpclient实现的 feign框架
 * 即可删除 RestTemplate 代码块（一直new，有点风险）
 *
 * 1 在pom中引入 spring-cloud-starter-openfeign的依赖  查看pom中的注释
 * 2 在启动类添加feign注解
 * 3 添加接口并添加注解@FeignClient(name = "product-service")
 *
 * -------------------------------------------------------------------
 * 启动类注解越来越多，可以采用 @SpringCloudApplication注解来优化/封装
 * -------------------------------------------------------------------
 *
 * 增加Hystrix模块，提供服务降级和熔断机制
 *
 * 1 在pom中引入，spring-cloud-starter-netflix-hystrix的依赖 查看pom中的注释
 * 2 在yml配置文件中开启Hystrix
 * 3 在启动类添加注解EnableCircuitBreaker
 * 4 在需要提供服务降级的服务中实现降级服务代码
 * 		1) 查看 OrderController 代码 降级的兜底数据  （整体降级）
 *		2) 查看 failback包下的类   （单服务降级）
 */
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
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

