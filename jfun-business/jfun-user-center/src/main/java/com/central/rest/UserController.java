package com.central.rest;

import com.central.entity.Role;
import com.central.entity.User;
import com.central.service.IRoleService;
import com.central.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @Auther: miv
 * @Date: 2019-05-21 19:26
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;
    @GetMapping("user")
    public ResponseEntity user(String username){
       User user = userService.getByUsername(username);
       Set<Role> roleSet = roleService.findRoleByUid(user.getId());
       user.setRoles(roleSet);
       return ResponseEntity.ok(user);
    }
}
