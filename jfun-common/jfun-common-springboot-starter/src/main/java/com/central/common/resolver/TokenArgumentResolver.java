package com.central.common.resolver;

import cn.hutool.core.util.StrUtil;
import com.central.common.annotation.LoginUser;
import com.central.common.constant.SecurityConstants;
import com.central.common.feign.AppUserService;
import com.central.common.model.Role;
import com.central.common.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Token转化SysUser
 *
 * @author zlt
 * @date 2018/12/21
 */
@Slf4j
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {
    private AppUserService appUserService;

    public TokenArgumentResolver(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class) && methodParameter.getParameterType().equals(User.class);
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        boolean isFull = loginUser.isFull();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String username = request.getHeader(SecurityConstants.USER_HEADER);
        String roles = request.getHeader(SecurityConstants.ROLE_HEADER);
        if (StrUtil.isBlank(username)) {
            log.warn("resolveArgument error username is empty");
            return null;
        }
        User user;

        if (isFull) {
            //rpc获取完整信息
            user = appUserService.findByUsername(username);

        } else {
            user = new User();
            user.setName(username);
            List<Role> sysRoleList = new ArrayList<>();
            Arrays.stream(roles.split(",")).forEach(role -> {
                Role sysRole = new Role();
                sysRole.setName(role);
                sysRoleList.add(sysRole);
            });
            user.setRoles(sysRoleList);
        }

        return user;
    }
}
