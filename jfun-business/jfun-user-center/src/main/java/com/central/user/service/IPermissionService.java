package com.central.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.central.common.model.Permission;
import com.central.common.vo.Resource;
import com.central.user.vo.Page;
import com.central.user.vo.PermissionTreeItem;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
public interface IPermissionService extends IService<Permission> {
    Set<Permission> findByRoleId(long rid);

    IPage<Permission> selectPermission(Page page, Permission resource);

    int del(Long id);

    List<Permission> buildTree(List<Permission> records);

    List<PermissionTreeItem> buildSelectTree(List<PermissionTreeItem> list);

}
