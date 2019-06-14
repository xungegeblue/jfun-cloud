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
    Role findRoleByUid(Long rid);

    List<Role> findRoleListByUid(Long uid);

    IPage<Role> selectRoles(Page page, Role resource);

    int del(Long id);

    int update(Role resource);
    
    Role create(Role resource);

    void updatePermission(Role resources);

    void updateMenu(Role resource);
}
