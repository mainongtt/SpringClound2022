package com.example.controller;

import com.example.entities.CommonResult;
import com.example.entities.Payment;
import com.example.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    // 为了测试负载均衡使用的
    @Value("${server.port}")
    private String serverPort;
    @Autowired(required = false)
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult<Object> create(@RequestBody Payment payment){
        int res = paymentService.create(payment);
        log.info("插入一条记录");
        if(res > 0){
            return new CommonResult<Object>(200,"插入成功" + serverPort ,payment);
        }else{
            return new CommonResult<>(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Object> findPaymentById(@PathVariable Integer id){
        Payment res = paymentService.getById(id);
        if(res == null){
            return new CommonResult<>(444,"查询失败",null);

        }
        return new CommonResult<>(200,"查询成功:" + serverPort,res);
    }
}
