package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.entities.CommonResult;
import com.example.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {


    @GetMapping("/testA")
    public String testA(){
        return "-------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "--------testB";
    }

    @GetMapping("/testHotKey") //这个代表rest请求地址
// value = "testHotKey001" testHotKeyabc001 是当前方法的唯一标识
// blockHandler = "del_testHotKey" 方法出现问题之后的兜底方法， del_testHotKey 兜底方法名称
    @SentinelResource(value = "testHotKeyabc001", blockHandler = "del_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){

        return "------testHotKey";
    }

    public String del_testHotKey(String p1, String p2, BlockException exception){

        return "自定义提示：del_testHotKeyo(╥﹏╥)o...";
    }


}
