package com.central.resource.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Auther: miv
 * @Date: 2019-06-13 16:44
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Configuration
public class TokenStoreConfig {
    @Resource
    private DataSource dataSource ;

    @Bean
    @ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="jdbc" ,matchIfMissing=true)
    public JdbcTokenStore jdbcTokenStore(){
        return new JdbcTokenStore( dataSource ) ;
    }
}
