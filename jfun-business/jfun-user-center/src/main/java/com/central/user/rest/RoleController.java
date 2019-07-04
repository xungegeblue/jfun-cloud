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

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author: miv
 * @Date: 2019-06-08 04:54
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class RoleController {

    @Autowired
    IRoleService roleService;

    /**
     * 查询角色
     * @param page
     * @param resource
     * @return
     */
    @GetMapping(value = "/role")
    public ResponseEntity find(Page page, Role resource) {
        IPage<Role> iPage = roleService.selectRoles(page, resource);
        return ResponseEntity.ok(iPage);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity getRole(@PathVariable Long id) {
        Role role = roleService.findRoleByUid(id);
        return new ResponseEntity(role, HttpStatus.OK);
    }

    @GetMapping(value = "/role/roleList")
    public ResponseEntity roleList() {
        Object o = roleService.list().stream().map(role -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", role.getId());
            jsonObject.put("label", role.getRemark());
            return jsonObject;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(o);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        roleService.del(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改角色对应的菜单和权限
     * @param id
     * @param resource
     * @return
     */
    @PutMapping(value = "/role/{id}")
    public ResponseEntity editMenuAndPermsssion(@PathVariable Long id, @Validated @RequestBody Role resource) {
        roleService.update(resource);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改角色
     * @param resource
     * @return
     */
    @PutMapping(value = "/role")
    public ResponseEntity edit(@RequestBody Role resource) {
        roleService.update(resource);
        return ResponseEntity.ok().build();
    }

    /**
     * 添加角色
     * @param resource
     * @return
     */
    @PostMapping(value = "/role")
    public ResponseEntity create(@Validated @RequestBody Role resource) {
        roleService.create(resource);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/role/permission")
    public ResponseEntity permission(@RequestBody Role resources) {
        roleService.updatePermission(resources);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/role/menu")
    public ResponseEntity menu(@RequestBody Role resource) {
        roleService.updateMenu(resource);
        return ResponseEntity.ok().build();
    }
}
