package com.central.zuul.service.impl;

import com.central.common.vo.Resource;
import com.central.zuul.service.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author: miv
 * @Date: 2019-06-21 16:08
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Service("authenticationService")
@Slf4j
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    ResourceServiceImpl resourceService;


    public static void main(String[] args) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String visitUrl = "/api-user/user";
        Set<Resource> resourceSet = new HashSet<>();

        Resource r = new Resource();
        r.setMethod("GET");
        r.setUrl("/api-user/user/*");
        resourceSet.add(r);

        System.out.println(antPathMatcher.match("/api-user/user/**", visitUrl));
    }

    @Override
    public boolean hasPermissions(HttpServletRequest authRequest, Authentication authentication) {
        log.debug("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        final String uri = authRequest.getRequestURI();

        //获取数据库里面的资源信息
        String tmp = (String) authentication.getName();
        Set<Resource> resourceSet = resourceService.findAll(authentication.getName());


        //鉴权
        Optional<Resource> desc = resourceSet.stream().filter(new Predicate<Resource>() {
            @Override
            public boolean test(Resource resource) {
                return  StringUtils.isNotEmpty(resource.getUrl())
                        && antPathMatcher.match(resource.getUrl(), uri)
                        && StringUtils.isNotEmpty(resource.getMethod())
                        && authRequest.getMethod().toLowerCase().equals(resource.getMethod().toLowerCase());



            }
        }).peek(resource -> log.info("匹配权限成功：{}", resource.toString())).findFirst();


        return desc.isPresent();

    }

    @Autowired
    HandlerMappingIntrospector mvcHandlerMappingIntrospector;


}
