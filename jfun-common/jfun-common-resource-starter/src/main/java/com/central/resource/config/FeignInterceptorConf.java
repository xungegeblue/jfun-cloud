package com.central.resource.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @Auther: miv
 * @Date: 2019-06-14 00:17
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 实现微服务直接的调用token的问题
 */
@Configuration
public class FeignInterceptorConf {
    @Bean
    public RequestInterceptor requestInterceptor() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate template) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null) {
                    if (authentication instanceof OAuth2Authentication) {
                        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                        String access_token = details.getTokenValue();
                        template.header("Authorization", OAuth2AccessToken.BEARER_TYPE + " " + access_token);
                    }

                }
            }
        };

        return requestInterceptor;
    }
}
