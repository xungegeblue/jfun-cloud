package com.central.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.central.common.model.Menu;
import com.central.common.model.Permission;
import com.central.common.model.Role;
import com.central.user.vo.Page;

import java.util.List;
import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
public interface IRoleService extends IService<Role> {
    List<Role> findRoleByUid(Long uid);

    IPage<Role> selectRoles(Page page, Role resource);

    int del(Long id);

    int update(Role resource);

    int update(Long id, Set<Menu> menus, Set<Permission> permissions);

    Role create(Role resource);
}
