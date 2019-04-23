package jfun.service.impl;


import jfun.entity.Role;
import jfun.service.IPermissionService;
import jfun.service.IRoleService;
import jfun.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service("userDetailsService")
@Slf4j
public class JfunUserDetailsService implements UserDetailsService {

    @Autowired
    IUserService iUserService;

    @Autowired
    IRoleService iRoleService;

    @Autowired
    IPermissionService iPermissionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        jfun.entity.User u = iUserService.getByUsername(s);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (u != null) {
            // 获取用户角色
            Set<Role> roles = iRoleService.findRoleByUid(u.getId());
            // 根据角色查询权限并且设置
            grantedAuthorities = getGrantedAuthorityByRole(roles);
        }
        return new User(u.getName(), u.getPassword(), grantedAuthorities);
    }

    private List<GrantedAuthority> getGrantedAuthorityByRole(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getPermissions())
                .flatMap(Collection::stream)
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }
}
