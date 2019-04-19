package jfun.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @Auther: miv
 * @Date: 2019-04-19 22:54
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Bean("jdbcTokenStore")
    public JdbcTokenStore jdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean("jdbcClientDetailsService")
    public JdbcClientDetailsService getJdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }


    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(getJdbcClientDetailsService());
    }

    /**
     *用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }
}
