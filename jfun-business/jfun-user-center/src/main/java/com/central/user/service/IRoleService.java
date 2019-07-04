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
    /**
     * 查询角色
     * @param rid
     * @return
     */
    Role findRoleByUid(Long rid);

    /**
     * 查询用户角色
     * @param uid
     * @return
     */
    List<Role> findRoleListByUid(Long uid);

    /**
     * 查询角色列表
     * @param page
     * @param resource
     * @return
     */
    IPage<Role> selectRoles(Page page, Role resource);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int del(Long id);

    /**
     * 更新角色
     * @param resource
     * @return
     */
    int update(Role resource);

    /**
     * 创建角色
     * @param resource
     * @return
     */
    Role create(Role resource);

    /**
     * 更新角色权限
     * @param resources
     */
    void updatePermission(Role resources);

    /**
     * 更新角色菜单
     * @param resource
     */
    void updateMenu(Role resource);
}
