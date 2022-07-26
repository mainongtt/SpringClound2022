package com.example.controller;

import com.example.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pagement/zk")
    public CommonResult<Object> getZk(){
        String data = "port :" + port + " " + UUID.randomUUID();
        return new CommonResult<>(200,data);
    }
}
