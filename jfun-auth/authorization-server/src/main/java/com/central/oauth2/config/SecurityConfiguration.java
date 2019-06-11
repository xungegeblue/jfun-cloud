package com.central.oauth2.config;

import com.central.common.constant.SecurityConstants;
import com.central.oauth2.constants.FromLoginConstant;
import com.central.oauth2.properties.SecurityProperties;
import com.central.oauth2.service.SQLUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Auther: miv
 * @Date: 2019-04-22 16:50
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


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
}
