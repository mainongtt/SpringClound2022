package com.example.controller;

import com.example.entities.CommonResult;
import com.example.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    //模拟一个表
    static{
        hashMap.put(1L, new Payment(1L, "qwertyuiop"));
        hashMap.put(2L, new Payment(2L, "asdfghjkl;"));
        hashMap.put(3L, new Payment(3L, "zxcvbnm,."));
    }
    //模拟查表
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,
                "from mysql, serverPORT: "+serverPort, payment);
        return result;
    }
}
