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
    /**
     * 获取角色的权限
     * @param rid
     * @return
     */
    Set<Permission> findByRoleId(long rid);

    /**
     * 获取权限
     * @param page
     * @param resource
     * @return
     */
    IPage<Permission> selectPermission(Page page, Permission resource);

    /**
     * 删除
     * @param id
     * @return
     */
    int del(Long id);

    /**
     * 构造树形结构
     * @param records
     * @return
     */
    List<Permission> buildTree(List<Permission> records);

    /**
     *获取权限树形结构
     * @param list
     * @return 权限信息
     */
    List<PermissionTreeItem> buildSelectTree(List<PermissionTreeItem> list);

}
