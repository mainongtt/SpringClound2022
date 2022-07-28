package com.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_OK,id: "+id+"/t"+"O(∩_∩)O";
    }

    // HystrixCommand 超时服务时的处理
    // fallbackMethod 如果当前方法出现了问题，由 paymentInfo_TimeOutHandler 这个方法兜底，给个友好提示
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            // 设置处理业务的超时时间为3s，我们这里故意5s，就会使用兜底方法处理
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id: "+id+"/t"+"O(∩_∩)O，耗费3秒";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        log.info("触发兜底方法");
        return "触发兜底方法";
    }
}
