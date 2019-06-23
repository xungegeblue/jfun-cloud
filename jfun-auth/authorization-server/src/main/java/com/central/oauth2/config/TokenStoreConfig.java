package com.central.oauth2.config;

import com.central.oauth2.token.ResJwtAccessTokenConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Auther: miv
 * @Date: 2019-06-13 16:44
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Slf4j
@Configuration
public class TokenStoreConfig {
    @Resource
    private DataSource dataSource;

    @Resource
    RedisProperties redisProperties;

    @Autowired(required = false)
    private RedisTemplate<String, Object> redisTemplate;


    @Bean
    @ConditionalOnProperty(prefix = "jfun", name = "tokenStoreType", havingValue = "jdbc")
    public JdbcTokenStore jdbcTokenStore() {
        log.info("-------------> 使用jdbc token");
        return new JdbcTokenStore(dataSource);
    }


    @Bean
    @ConditionalOnProperty(prefix = "jfun", name = "tokenStoreType", havingValue = "redis")
    public RedisTokenStore redisStore(RedisConnectionFactory redisConnectionFactory) {
        //redis 集群
        log.info("-------------> 使用redis token");
        RedisTokenStore redisTemplateStore = new RedisTokenStore(redisConnectionFactory);
        return redisTemplateStore;
    }

    @Configuration
    @ConditionalOnProperty(prefix = "jfun", name = "type", havingValue = "tokenStoreType")
    public static class JWTTokenConfig {
        @Bean
        public JwtTokenStore jwtTokenStore() {
            log.info("-------------> 使用jwt token");
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            //    keytool -genkeypair -alias jfuncloud -keyalg RSA -keypass jfuncloud -keystore jfuncloud.jks -storepass jfuncloud
            //     keytool -list -rfc --keystore jfuncloud.jks | openssl x509 -inform pem -pubkey
            //使用非对称加密
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            KeyStoreKeyFactory keyStoreKeyFactory =
                    new KeyStoreKeyFactory(new ClassPathResource("jfuncloud.jks"), "jfuncloud".toCharArray());
            converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jfuncloud"));
            converter.setAccessTokenConverter(new ResJwtAccessTokenConverter());
            return converter;
        }
    }
}