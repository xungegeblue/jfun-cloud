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

    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column = "id", property = "permissions", many = @Many(select = "com.central.user.dao.PermissionMapper.findByRoleId"))
    })
    @Select("select * from sys_role where id in(select role_id as  id from sys_user_role where uid=#{uid})")
    List<Role> findRoleByUid(@Param("uid") Long uid);


    @ResultType(value = java.lang.Integer.class)
    @Select({"select count(1) from sys_user_role where role_id=#{roleId}"})
    int selectBinds(@Param("roleId") Long roleId);

    @Delete("delete sys_role_permission where role_id=#{roleId}")
    void delRelationPermissions(@Param("roleId") Long roleId);

    @Delete("delete sys_roles_menu where role_id=#{roleId}")
    void delRelationMenus(@Param("roleId") Long roleId);


    int andRelationPermissions(@Param("roleId")Long roleId,@Param("menuIds") List<Long> menuIds);


    int andRelationMenus(@Param("roleId")Long roleId, @Param("permissionIds")List<Long> permissionIds);


    IPage<Role> selectRolesPage(Page page, @Param(value = "role") Role role);
}
