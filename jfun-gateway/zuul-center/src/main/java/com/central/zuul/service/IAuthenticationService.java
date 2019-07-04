package com.central.zuul.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: miv
 * @Date: 2019-06-21 16:06
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 统一鉴权
 */
public interface IAuthenticationService {
    /**
     * 判断是否有权限
     * @param authRequest
     * @param authentication
     * @return
     */
    boolean hasPermissions(HttpServletRequest authRequest, Authentication authentication);
}
