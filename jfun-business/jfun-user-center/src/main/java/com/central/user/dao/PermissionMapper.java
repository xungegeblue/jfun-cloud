package com.central.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.Permission;
import com.central.common.vo.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    //查询角色的所有权限
    @Select("SELECT p.* from sys_role_permission up LEFT JOIN sys_permission p ON(up.permission_id = p.id) WHERE up.role_id = #{rid} ")
    Set<Permission> findByRoleId(@Param("rid") long rid);

    @Select("select count(1) from sys_role_permission where permission_id=#{pid}")
    int permissionBinds(@Param("pid") Long pid);


}
