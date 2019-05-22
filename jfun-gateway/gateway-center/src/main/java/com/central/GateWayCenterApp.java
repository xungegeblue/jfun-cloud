package com.central;

import com.alibaba.nacos.api.annotation.NacosProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author 谢镜勋
 * @Date 2019/4/24
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayCenterApp {
    NacosProperties nacosProperties;

    public static void main(String[] args) {
        SpringApplication.run(GateWayCenterApp.class, args);
    }
}
