package com.central;

import com.central.common.annotation.EnableLoginArgResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: miv
 * @Date: 2019-05-18 17:45
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@EnableFeignClients
@EnableLoginArgResolver
@EnableDiscoveryClient
@SpringBootApplication
public class UserCneter {
    public static void main(String[] args) {
        SpringApplication.run(UserCneter.class, args);
    }
}
