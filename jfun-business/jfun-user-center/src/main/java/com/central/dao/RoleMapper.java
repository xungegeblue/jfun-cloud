package com.central.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Results({
          @Result(column = "id", property = "permissions", many = @Many(select = "com.central.dao.PermissionMapper.findByRoleId"))
    })
    @Select("select * from sys_role where id in(select role_id as  id from sys_user_role where uid=#{uid})")
    Set<Role> findRoleByUid(@Param("uid") Long uid);

}
