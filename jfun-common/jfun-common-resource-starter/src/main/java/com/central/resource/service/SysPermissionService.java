package com.central.resource.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: miv
 * @Date: 2019-06-21 19:19
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
public interface SysPermissionService {

    /**
     * 判断请求是否有权限
     * @param request
     * @param authentication
     * @return
     */
    Boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
