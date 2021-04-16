package cn.cocowwy.orange.orangewall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//开启服务发现
@EnableDiscoveryClient
//开启服务调用
@EnableFeignClients
public class OrangeWallApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrangeWallApplication.class, args);
    }
}
