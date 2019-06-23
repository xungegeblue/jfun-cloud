package com.central.user.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.central.common.annotation.LoginUser;
import com.central.common.model.Role;
import com.central.common.model.User;
import com.central.common.vo.LoginAppUser;
import com.central.user.service.IRoleService;
import com.central.user.service.IUserService;
import com.central.user.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.*;

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

    @GetMapping("/user/info")
    public User info(@LoginUser(isFull = true) User user){
        return user;
    }

    @GetMapping("findByUsername")
    public LoginAppUser findByUsername(String username){
       User user = userService.getByUsername(username);
       LoginAppUser loginAppUser = userService.getLoginAppUser(user);
       return loginAppUser;
    }

    //查询用户
    @GetMapping(value = "/user")
    public ResponseEntity user(Page page,User resource){
        IPage<User> iPage = userService.selectUserPage(page, resource);
        return ResponseEntity.ok(iPage);
    }


    //删除用户
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //修改用户
    @PutMapping(value = "/user")
    public ResponseEntity edit(@Validated @RequestBody User resource) {
        userService.update(resource);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    //添加用户
    @PostMapping(value = "/user")
    public ResponseEntity create(@Validated @RequestBody User resource){
        userService.create(resource);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
