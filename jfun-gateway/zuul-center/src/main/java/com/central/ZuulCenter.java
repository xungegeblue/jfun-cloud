package com.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: miv
 * @Date: 2019-05-23 23:52
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ZuulCenter {

    // TODO 统一授权(先实现简单的匹配）

    public static void main(String[] args) {
        SpringApplication.run(ZuulCenter.class, args);
    }


}
