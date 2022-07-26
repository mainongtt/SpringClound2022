package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.entities.CommonResult;
import com.example.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResourceQWER", blockHandler = "handlerException")
    public CommonResult byResource(){

        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }
    public CommonResult handlerException(BlockException e){
        return new CommonResult(404, e.getClass().getCanonicalName()+"/t 服务不可用");
    }


    @GetMapping("/rateLimit/byUrl")
    @SentinelResource
    public CommonResult byUrl(){
        return new CommonResult(200, "按照URL限流测试OK", new Payment(2020L, "serial002"));
    }


    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handleException1")
    public CommonResult customerBlockHandler(){

        return new CommonResult(200, "全局的客户自定义处理", new Payment(2020L, "serial003"));
    }
}
