package com.central.oauth2.config;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.central.oauth2.po.JfunTokenEnhancer;
import com.netflix.ribbon.proxy.annotation.ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Auther: miv
 * @Date: 2019-04-22 16:46
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Qualifier("dataSource")

    @Autowired
    DataSource dataSource;
    //定义令牌端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {


        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
                .authorizationCodeServices(authorizationCodeServices())
                .reuseRefreshTokens(true);
    }
    //定义令牌端点约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();

    }

    //客户端信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        builder
                //设置客户端和密码
                .withClient("client_1").secret("123456")
                //设置token有效期
                .accessTokenValiditySeconds(7 * 24 * 3600)
                //设置refreshToken有效期
                .refreshTokenValiditySeconds(7 * 24 * 3600)
                //支持的认证方式
                    .authorizedGrantTypes("refresh_token", "authorization_code", "password").autoApprove(false)
                //授权域
                .scopes("select","app");

         */
        JdbcClientDetailsServiceBuilder builder = clients.jdbc(dataSource);

    }


    //=================================

    //token
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(new JwtAccessTokenConverter());
    }
    //授权码
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

}
