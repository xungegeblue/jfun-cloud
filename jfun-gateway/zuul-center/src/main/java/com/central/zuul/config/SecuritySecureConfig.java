package com.central.zuul.config;

import com.central.common.config.PermitUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author: miv
 * @Date: 2019-06-15 07:35
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(PermitUrlProperties.class)
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PermitUrlProperties permitUrlProperties;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(permitUrlProperties.getIgnored());
    }

}
