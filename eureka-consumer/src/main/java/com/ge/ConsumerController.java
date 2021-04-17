package com.ge;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    DiscoveryClient client; // 导入spring cloud的抽象接口，而不是netflix的接口

    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient loadBalancerClient;


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("client")
    public String client() {
        client.getServices().forEach(System.out::println);
        //eureka-server
        //eureka-provider
        //eureka-consumer
        return "client";
    }

    @GetMapping("client2")
    public Object client2() {
        return client.getInstances("eureka-provider");
    }

    @GetMapping("client3")
    public Object client3() {
        client.getInstances("eureka-provider").
                forEach(serviceInstance ->
                        System.out.println(ToStringBuilder.reflectionToString(serviceInstance)));

        return "client3";
    }

    @GetMapping("client4")
    public Object client4() {
        //List<ServiceInstance> instances = client.getInstances("eureka-provider");

        // 注意getInstancesById，参数是instanceID
        //List<InstanceInfo> instances1 = client2.getInstancesById("host.docker.internal:eureka-provider:80");

        // getInstancesByVipAddress这个方法的参数才是app, 第二个参数的意思是不需要权限校验
        List<InstanceInfo> instances1 = client2.getInstancesByVipAddress("eureka-provider", false);
        if (!instances1.isEmpty()) {
            InstanceInfo instanceInfo = instances1.get(0);
            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/hello";
                System.out.println("url: " + url);
                RestTemplate restTemplate = new RestTemplate();
                String response = restTemplate.getForObject(url, String.class);
                System.out.println("resp: " + response);
            }
        }

        return "client4";
    }

    @GetMapping("client5")
    public Object client5() {
        // ribbon 完成客户端的负载均衡，它内部会过滤掉状态=down的节点
        ServiceInstance instance = loadBalancerClient.choose("eureka-provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hello";
        System.out.println("url: " + url);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("resp: " + response);
        return response;
    }
}
