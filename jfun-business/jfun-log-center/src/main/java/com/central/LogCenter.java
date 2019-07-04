package com.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: miv
 * @Date: 2019-06-13 18:42
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */

@EnableDiscoveryClient
@SpringBootApplication
public class LogCenter {
    public static void main(String[] args) {
        SpringApplication.run(LogCenter.class, args);
    }
}
