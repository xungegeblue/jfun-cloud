package jfun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

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

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置两个客户端,一个用于password认证一个用于client认证

        /**数据保存在内存中
        clients.inMemory().withClient("client_1")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials")
                .scopes("select")
                .authorities("oauth2")
                .secret("123456")
                .and().withClient("client_2")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret("123456");
        */
        //客户端数据保存在数据库中,会读取这个配置
        clients.jdbc(dataSource);
//                .withClient("client_1")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("client_credentials")
//                .scopes("select")
//                .authorities("test")
//                .secret("123456");

    }


    /**
     * 授权码模式持久化授权码code
     *
     * @return JdbcAuthorizationCodeServices
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        //授权码存储等处理方式类，使用jdbc，操作oauth_code表
        return new JdbcAuthorizationCodeServices(dataSource);
    }
    /**
     * 授权信息持久化实现
     * @return JdbcApprovalStore
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authorizationCodeServices(authorizationCodeServices())
                .approvalStore(approvalStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                // 2018-4-3 增加配置，允许 GET、POST 请求获取 token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        endpoints.reuseRefreshTokens(true);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();;
    }
}
