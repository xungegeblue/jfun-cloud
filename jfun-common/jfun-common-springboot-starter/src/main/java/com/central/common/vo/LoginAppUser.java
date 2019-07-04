package com.central.common.vo;

import com.central.common.model.Role;
import com.central.common.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: miv
 * @Date: 2019-06-09 14:35
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Setter
@Getter
public class LoginAppUser extends User implements SocialUserDetails {
    @Override
    public String getUserId() {
        return getId() + "";
    }
    private Set<String> permissions;
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();

        if (!CollectionUtils.isEmpty(getRoles())) {
            collection = getRoles().stream()
                    .map(role -> role.getPermissions())
                    .flatMap(Collection::stream)
                    .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                    .collect(Collectors.toList());
        }
        return collection;
    }



    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
