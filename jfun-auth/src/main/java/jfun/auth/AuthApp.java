package jfun.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @Auther: miv
 * @Date: 2019-04-19 22:48
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:认证中心
 */
@SpringCloudApplication
@EnableAuthorizationServer
public class AuthApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class,args);
    }
}
