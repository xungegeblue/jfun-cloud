package com.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.client.RestTemplate;

/**
 * @author: miv
 * @Date: 2019-04-22 16:53
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthorizationServerApp {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApp.class, args);
    }


    // TODO 1.加上验证码
    // TODO 2.手机验证码登陆

}
