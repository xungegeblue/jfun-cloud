package com.central.oauth2.service;

import com.central.common.feign.UserService;
import com.central.common.model.Role;
import org.springframework.security.core.userdetails.User;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class SQLUserDetailsService implements UserDetailsService {

//    @Autowired
//    RestTemplate restTemplate;


    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //String user =  restTemplate.getForObject("http://user-center/user?username="+name , String.class);
        Object obj = userService.findByUsername(name);
        Gson gson = new Gson();
        String userJson = gson.toJson(obj);
        com.central.common.model.User user = gson.fromJson(userJson, com.central.common.model.User.class);
        User authUser = new User(user.getName(), user.getPassword(), getGrantedAuthorityByRole(user.getRoles()));
        log.info(authUser.toString());

        return authUser;
    }

    private List<GrantedAuthority> getGrantedAuthorityByRole(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getPermissions())
                .flatMap(Collection::stream)
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }

}
