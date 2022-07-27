package com.example.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    /**
     *  获取所有的服务实例中我们需要的那个实例
     * @param serviceInstances 所有的服务实例
     * @return 我们需要的服务实例
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
