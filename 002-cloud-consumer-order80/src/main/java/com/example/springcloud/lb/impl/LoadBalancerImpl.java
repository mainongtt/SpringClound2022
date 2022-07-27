package com.example.springcloud.lb.impl;

import com.example.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerImpl implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    private final int getIncrement() {
        int current = 0;
        int next = 0;
//        do {
//            current = this.nextServerCyclicCounter.get();
//            next = (current + 1) % modulo;
//        } while(!this.nextServerCyclicCounter.compareAndSet(current, next));
        do {
            current = atomicInteger.get();
            next = current > Integer.MAX_VALUE ? 0 : current + 1;
        }while (!atomicInteger.compareAndSet(current,next));
        return next;
    }


}
