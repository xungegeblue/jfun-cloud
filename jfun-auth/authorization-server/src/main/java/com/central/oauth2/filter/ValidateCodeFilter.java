package com.central.oauth2.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: miv
 * @Date: 2019-05-31 18:12
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 校验验证码
 */
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter  extends OncePerRequestFilter {


    private AntPathMatcher pathMatcher = new AntPathMatcher();

    //返回true代表不执行这个过滤器
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return super.shouldNotFilter(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        doFilter(request,response,filterChain);
    }
}
