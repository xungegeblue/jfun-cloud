package com.central.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @Auther: miv
 * @Date: 2019-05-23 23:52
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulCenter {
    public static void main(String[] args) {
        SpringApplication.run(ZuulCenter.class,args);
    }


}
