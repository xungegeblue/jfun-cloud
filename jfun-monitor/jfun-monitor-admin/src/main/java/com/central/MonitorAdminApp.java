package com.central;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: miv
 * @Date: 2019-06-14 23:40
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class MonitorAdminApp {
    public static void main(String[] args) {
        //https://www.vojtechruzicka.com/spring-boot-admin/
        SpringApplication.run(MonitorAdminApp.class,args);
    }
}
