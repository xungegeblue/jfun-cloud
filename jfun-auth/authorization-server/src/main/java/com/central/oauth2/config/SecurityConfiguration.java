package com.central.oauth2.config;

import com.central.common.config.PermitUrlProperties;
import com.central.oauth2.constants.FromLoginConstant;
import com.central.oauth2.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: miv
 * @Date: 2019-04-22 16:50
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(PermitUrlProperties.class)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PermitUrlProperties permitUrlProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    SecurityProperties securityProperties = new SecurityProperties();

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http
                .formLogin().loginPage(FromLoginConstant.LOGIN_PAGE).loginProcessingUrl(FromLoginConstant.LOGIN_PROCESSING_URL)
                .and()
                .authorizeRequests().antMatchers(
                "/oauth/**",
                FromLoginConstant.LOGIN_PROCESSING_URL,
                FromLoginConstant.LOGIN_PAGE,
                securityProperties.getOauthLogin().getOauthLogin(),
                securityProperties.getOauthLogin().getOauthGrant()).permitAll().anyRequest().authenticated().and()
                .csrf().disable();
        // @formatter:on

    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(permitUrlProperties.getIgnored());
    }
}
