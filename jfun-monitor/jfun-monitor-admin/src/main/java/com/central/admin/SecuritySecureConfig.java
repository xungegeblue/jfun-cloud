package com.central.admin;

import com.central.common.config.PermitUrlProperties;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl("/");

        http.authorizeRequests()
                .antMatchers("/assets/**").permitAll()//Grants public access to all static assets and the login page.
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()//	Every other request must be authenticated.
                .and()
                .formLogin().loginPage("/login").successHandler(successHandler).and()//Configures login and logout.
                .logout().logoutUrl("/logout").and()
                .httpBasic().and()//Enables HTTP-Basic support. This is needed for the Spring Boot Admin Client to register.
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//	Enables CSRF-Protection using Cookies
                .ignoringAntMatchers(
                        "/instances",//	Disables CRSF-Protection the endpoint the Spring Boot Admin Client uses to register.
                        "/actuator/**"//Disables CRSF-Protection for the actuator endpoints.
                );
    }
}
