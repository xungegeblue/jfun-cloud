package com.central.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * @author: miv
 * @Date: 2019-04-22 16:46
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;


    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 定义令牌端点
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .authorizationCodeServices(authorizationCodeServices())
                .reuseRefreshTokens(false);
        // jwt支持
        if (jwtAccessTokenConverter != null) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter);
        }

    }

    /**
     * 定义令牌端点约束
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();


    }

    /**
     * 客户端信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        JdbcClientDetailsServiceBuilder builder = clients.jdbc(dataSource);

    }

    /**
     * 使用jdbc数据源的授权码
     * @return
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

}
