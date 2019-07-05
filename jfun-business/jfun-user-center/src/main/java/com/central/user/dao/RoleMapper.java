package com.central.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.central.common.model.Menu;
import com.central.common.model.Permission;
import com.central.common.model.Role;
import com.central.user.vo.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 获取用户角色
     * @param uid
     * @return
     */
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "permissions", many = @Many(select = "com.central.user.dao.PermissionMapper.findByRoleId")),
            @Result(column = "id", property = "menus", many = @Many(select = "com.central.user.dao.MenuMapper.findByRoleId"))
    })
    @Select("select * from sys_role where id in(select role_id as  id from sys_user_role where uid=#{uid})")
    List<Role> findRoleListByUid(@Param("uid") Long uid);

    /**
     * 根据角色ID获取角色
     * @param rid
     * @return
     */
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "permissions", many = @Many(select = "com.central.user.dao.PermissionMapper.findByRoleId")),
            @Result(column = "id", property = "menus", many = @Many(select = "com.central.user.dao.MenuMapper.findByRoleId"))
    })
    @Select("select * from sys_role where id in(select role_id as  id from sys_user_role where uid=#{uid})")
    Role findRoleByUid(@Param("uid") Long uid);

    /**
     * 获取角色是否被使用
     * @param roleId
     * @return
     */
    @ResultType(value = java.lang.Integer.class)
    @Select({"select count(1) from sys_user_role where role_id=#{roleId}"})
    int selectBinds(@Param("roleId") Long roleId);

    /**
     * 删除角色ID对于的权限
     * @param roleId
     */
    @Delete("delete from sys_role_permission where role_id=#{roleId}")
    void delRelationPermissions(@Param("roleId") Long roleId);

    /**
     * 删除角色ID对于的菜单
     * @param roleId
     */
    @Delete("delete from sys_role_menu where role_id=#{roleId}")
    void delRelationMenus(@Param("roleId") Long roleId);

    /**
     * 给角色绑定权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    int andRelationPermissions(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    /**
     * 给角色绑定菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    int andRelationMenus(@Param("roleId") Long roleId, @Param("menuIds") List<Long> menuIds);

    /**
     * 查询角色列表
     * @param page
     * @param role
     * @return
     */
    IPage<Role> selectRolesPage(Page page, @Param(value = "role") Role role);
}
