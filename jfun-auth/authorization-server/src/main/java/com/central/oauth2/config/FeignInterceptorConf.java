package com.central.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.context.annotation.Configuration;

/**
 * @author: miv
 * @Date: 2019-06-14 00:17
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
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
                        String accessToken = details.getTokenValue();
                        template.header("Authorization", OAuth2AccessToken.BEARER_TYPE + " " + accessToken);
                    }

                }
            }
        };

        return requestInterceptor;
    }
}
