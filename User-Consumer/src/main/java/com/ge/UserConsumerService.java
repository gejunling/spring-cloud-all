package com.ge;

//@FeignClient(name = "自定义client名称",url = "http://localhost:81")
// 不结合eureka，就是自定义一个client名字，就用url属性指定服务器列表。url="http://ip:port"

//@FeignClient(name = "user-provider")
public interface UserConsumerService extends RegisterApi {

}