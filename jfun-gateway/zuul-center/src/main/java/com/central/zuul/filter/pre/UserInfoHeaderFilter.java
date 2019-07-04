package com.central.zuul.filter.pre;

import cn.hutool.core.collection.CollectionUtil;
import com.central.common.constant.SecurityConstants;
import com.central.zuul.service.impl.AuthenticationServiceImpl;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;

/**
 * 将认证用户的相关信息放入header中, 后端服务可以直接读取使用
 *
 * @author zlt
 * @date 2018/11/20
 */
@Component
public class UserInfoHeaderFilter extends ZuulFilter {

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RequestContext ctx = RequestContext.getCurrentContext();

        //设置用户信息
        if (authentication != null && (authentication instanceof OAuth2Authentication)) {
            String username = authentication.getName();

            ctx.addZuulRequestHeader(SecurityConstants.USER_HEADER, username);
            ctx.addZuulRequestHeader(SecurityConstants.ROLE_HEADER, CollectionUtil.join(authentication.getAuthorities(), ","));
        }


        return null;
    }

}
