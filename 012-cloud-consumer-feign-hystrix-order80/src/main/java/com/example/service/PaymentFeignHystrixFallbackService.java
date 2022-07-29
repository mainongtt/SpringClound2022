package com.example.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFeignHystrixFallbackService implements PaymentHystrixService{
    @Override
    public String ok(Integer id) {
        return "payment/hystrix/ok 接口，降级成功";
    }

    @Override
    public String timeout(Integer id) {
        return "/consumer/payment/hystrix/timeout 接口，降级成功";
    }
}
