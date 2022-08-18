package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.entities.CommonResult;
import com.example.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircuitBreakerController {
    @Value("${server-url.nacos-user-service}")
    private String SERVER_URL;
    //public static final String SERVER_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")
    // 出现了运行时异常了，有 fallback 指向的方法进行兜底，
    // blockHandler负责的是sentinel配置错误兜底
    @SentinelResource(value = "fallback", fallback = "handleFallBack",
            blockHandler = "handleBlockHandler", exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/"+id, CommonResult.class, id);
        if(id==4){
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常....");
        }else if(result.getT() == null) {
            throw new NullPointerException("NullPointerException，该ID没有对应记录，空指针异常");
        }
        return result;
    }

    public CommonResult<Payment> handleFallBack(@PathVariable Long id, Throwable e){
        return new CommonResult(4444, "运行时异常兜底方法" + e.getMessage().toString());
    }

    public CommonResult<Payment> handleBlockHandler(@PathVariable Long id, BlockException blockException) {
        return new CommonResult<>(445, "setninel 的兜底方法接收到的异常 >>>>>>>> " + blockException.getMessage());
    }
}