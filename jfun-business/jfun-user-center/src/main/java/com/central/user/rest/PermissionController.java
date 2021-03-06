package com.central.user.rest;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.central.common.model.Permission;
import com.central.common.model.User;
import com.central.user.service.IPermissionService;
import com.central.user.vo.Page;
import com.central.user.vo.PermissionTreeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: miv
 * @Date: 2019-06-08 05:43
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    /**
     * 查询
     * @param page
     * @param resource
     * @return
     */
    @GetMapping(value = "/permission")
    public ResponseEntity selectPermission(Page page, Permission resource) {
        IPage<Permission> iPage = permissionService.selectPermission(page, resource);
        List<Permission> tree = permissionService.buildTree(iPage.getRecords());
        iPage.setRecords(tree);
        iPage.setTotal(tree.size());
        return ResponseEntity.ok(iPage);
    }


    @GetMapping(value = "/permission/tree")
    public ResponseEntity tree() {
        List<Permission> list = permissionService.list();
        List<PermissionTreeItem> records = list.stream().map(p -> {
            return new PermissionTreeItem(p.getId(), p.getAlias(), p.getPid());
        }).collect(Collectors.toList());
        List<PermissionTreeItem> o = permissionService.buildSelectTree(records);
        return ResponseEntity.ok(o);
    }


    @DeleteMapping(value = "/permission/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        permissionService.del(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/permission")
    public ResponseEntity edit(@Validated @RequestBody Permission resource) {
        permissionService.updateById(resource);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/permission")
    public ResponseEntity create(@Validated @RequestBody Permission resource) {
        resource.setCreateTime(DateUtil.date().toTimestamp());
        permissionService.save(resource);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
