package com.central.zuul.config;

import com.central.resource.config.OAuth2ClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsUtils;

/**
 * @author: miv
 * @Date: 2019-06-22 03:26
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 设置资源服务器
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends OAuth2ClientConfig {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().access("@authenticationService.hasPermissions(request,authentication)");

    }
}
