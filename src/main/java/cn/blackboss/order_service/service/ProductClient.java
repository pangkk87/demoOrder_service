package cn.blackboss.order_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 可以让服务提供方提供一个jar包，包含DTO和服务interface
 * 这样的好处是像调用本地方法一样，实质还是一个httpclient
 * 数据结构的处理，可以采用json字符串来处理，好处是在修改的时候不用更新DTO的jar包
 *
 * FeignClient(name = "product-service")
 * 对应在ruraka上注册的服务名称一致
 * 添加方法和服务提供方的方法及参数一致
 * 服务提供方如果使用对象 @requestBody时 这边一定使用PostMapping
 */
@FeignClient(name = "product-service")
public interface ProductClient {

    /**
     * springMvc的注解
     * 当调用findById 自动向product-service微服务的/api/v1/product/find发起请求
     * 添加RequestParam(value = "id")
     * 调用时，会自动把？id=xxx 附加到url中
     */
    @RequestMapping("/api/v1/product/find")
    String findById(@RequestParam(value = "id") int id);

    /**
     *  注意
     *
     *      Get请求对应GetMapping请求
     *          Get请求使用RequestParam注解发送参数
     *      Post请求对应PostMapping请求
     *          Post请求使用@requestBody注解发送参数
     *
     * 使用postMapping 注解
     * 参数用requestBody
     * 如下
     */
//    @PostMapping
//    public String create(@RequestBody Map map);
}
