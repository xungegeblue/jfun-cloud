package com.central.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: miv
 * @Date: 2019-06-03 16:48
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BackApp {
    public static void main(String[] args) {
        SpringApplication.run(BackApp.class,args);
    }
}
