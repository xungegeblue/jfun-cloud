package com.central.user.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.central.common.annotation.LoginUser;
import com.central.common.model.Role;
import com.central.common.model.User;
import com.central.user.service.IRoleService;
import com.central.user.vo.Page;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * @Auther: miv
 * @Date: 2019-06-08 04:54
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class RoleController {

    @Autowired
    IRoleService roleService;

    //查询角色
    @GetMapping(value = "/role")
    public ResponseEntity find(Page page, Role resource) {
        IPage<Role> iPage = roleService.selectRoles(page, resource);
        return ResponseEntity.ok(iPage);
    }

    @GetMapping(value = "/roleList")
    public ResponseEntity roleList() {
        Object o = roleService.list().stream().map(role -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", role.getId());
            jsonObject.put("label", role.getRemark());
            return jsonObject;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(o);
    }

    //删除角色
    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        roleService.del(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //修改角色对应的菜单和权限
    @PutMapping(value = "/role/{id}")
    public ResponseEntity editMenuAndPermsssion(@PathVariable Long id, @Validated @RequestBody Role resource) {
        roleService.update(id, resource.getMenus(), resource.getPermissions());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //修改角色
    @PutMapping(value = "/role")
    public ResponseEntity edit(Role resource) {
        roleService.update(resource);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //添加角色
    @PostMapping(value = "/role")
    public ResponseEntity create(@Validated @RequestBody Role resource) {
        roleService.create(resource);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
