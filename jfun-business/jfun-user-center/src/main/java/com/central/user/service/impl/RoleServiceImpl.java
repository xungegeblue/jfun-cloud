package com.central.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.common.model.Menu;
import com.central.common.model.Permission;
import com.central.user.dao.RoleMapper;
import com.central.common.model.Role;
import com.central.user.service.IRoleService;
import com.central.user.vo.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {


    @Override
    public Role findRoleByUid(Long rid) {
        return baseMapper.findRoleByUid(rid);
    }

    @Override
    public List<Role> findRoleListByUid(Long uid) {
        return baseMapper.findRoleListByUid(uid);
    }

    @Override
    public IPage<Role> selectRoles(Page page, Role resource) {
        return baseMapper.selectRolesPage(page, resource);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int del(Long id) {
        //如果该角色有对于的用户那么不予删除
        int binds = baseMapper.selectBinds(id);

        int i = baseMapper.deleteById(id);
        if (binds > 0) {
            throw new IllegalStateException();
        }
        //删除角色绑定的权限和菜单
        if (i > 0) {
            baseMapper.delRelationPermissions(id);
            baseMapper.delRelationMenus(id);
        }
        return i;
    }

    @Override
    public int update(Role resource) {
        return baseMapper.updateById(resource);
    }


    @Override
    public Role create(Role resource) {
        baseMapper.insert(resource);
        return resource;
    }

    @Override
    public void updatePermission(Role resources) {
        List<Long> permissionIds = resources.getPermissions().stream().map(s -> s.getId()).distinct().collect(Collectors.toList());
        baseMapper.delRelationPermissions(resources.getId());
        baseMapper.andRelationPermissions(resources.getId(), permissionIds);
    }

    @Override
    public void updateMenu(Role resource) {
        List<Long> menuIds = resource.getMenus().stream().map(s -> s.getId()).distinct().collect(Collectors.toList());
        baseMapper.delRelationMenus(resource.getId());
        baseMapper.andRelationMenus(resource.getId(), menuIds);
    }
}
