package com.example.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handleException1(BlockException exception) {
        return new CommonResult(4444, "客户自定义的全局异常 -------------- 1");
    }

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(4444, "客户自定义的全局异常 -------------- 1");
    }
}
