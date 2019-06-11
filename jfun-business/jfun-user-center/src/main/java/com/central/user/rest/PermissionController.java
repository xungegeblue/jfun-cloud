package com.central.user.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.central.common.model.Permission;
import com.central.common.model.User;
import com.central.user.service.IPermissionService;
import com.central.user.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: miv
 * @Date: 2019-06-08 05:43
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    //查询
    @GetMapping(value = "/permission")
    public ResponseEntity selectPermission(Page page, Permission resource) {
        IPage<Permission> iPage = permissionService.selectPermission(page, resource);
        List<Permission> tree = permissionService.buildTree(iPage.getRecords());
        iPage.setRecords(tree);
        iPage.setTotal(tree.size());
        return ResponseEntity.ok(iPage);
    }


    //删除
    @DeleteMapping(value = "/permission/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        permissionService.del(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //修改
    @PutMapping(value = "/permission")
    public ResponseEntity edit(@Validated @RequestBody Permission resource) {
        permissionService.updateById(resource);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //添加
    @PostMapping(value = "/permission")
    public ResponseEntity create(@Validated @RequestBody Permission resource) {
        permissionService.save(resource);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
