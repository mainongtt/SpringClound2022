package com.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE" , fallback = PaymentFeignHystrixFallbackService.class) // 使用 feign
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String timeout(@PathVariable("id") Integer id);

}
