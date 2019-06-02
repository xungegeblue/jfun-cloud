package com.central.oauth2.config;

import com.central.common.util.ResponseUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证错误处理
 *
 * @author zlt
 */
@Slf4j
@Configuration
public class SecurityHandlerConfig {
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 登陆失败，返回401
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            String msg;
            if (exception instanceof BadCredentialsException) {
                msg = "密码错误";
            } else {
                msg = exception.getMessage();
            }
            ResponseUtil.responseWriter(objectMapper, response, msg, HttpStatus.UNAUTHORIZED.value());
        };
    }



    /**
     * 登陆成功
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
}
