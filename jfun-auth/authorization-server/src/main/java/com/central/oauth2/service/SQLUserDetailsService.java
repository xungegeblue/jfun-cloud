package com.central.oauth2.service;

import com.central.common.feign.AppUserService;
import com.central.common.model.Role;
import com.central.common.vo.LoginAppUser;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
    AppUserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //String user =  restTemplate.getForObject("http://user-center/user?username="+label , String.class);
        LoginAppUser loginAppUser = userService.findByUsername(name);
        if (loginAppUser == null) {
            throw new InternalAuthenticationServiceException("用户名或密码错误");
        }
        return loginAppUser;
    }



}
