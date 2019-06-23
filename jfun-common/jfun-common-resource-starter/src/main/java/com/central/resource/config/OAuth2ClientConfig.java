package com.central.resource.config;

import com.central.resource.exception.AuthExceptionEntryPoint;
import com.central.resource.exception.CustomAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @Auther: miv
 * @Date: 2019-06-13 16:37
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
//@Slf4j
//@Component
//@Configuration
//@EnableResourceServer
public class OAuth2ClientConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
        resources.stateless(true);
        resources.authenticationEntryPoint(authExceptionEntryPoint);
        resources.expressionHandler(expressionHandler);
        resources.accessDeniedHandler(customAccessDeniedHandler);

    }

}
